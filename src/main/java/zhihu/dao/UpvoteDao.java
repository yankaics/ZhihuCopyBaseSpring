package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.Upvote;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZJ on 2016/4/17.
 */

@Repository
public class UpvoteDao {

	@Autowired
	JdbcOperations jdbcOperations;

	private final String QUERY_UPVOTE = "select * from upvote where ans_id = ? and user_id =?";

	private final String UPDATE_RECORD = "UPDATE upvote SET up = ?, down = ? where upvote_id = ?";

	private final String ADD_NEW_UPVOTE_RECORD = "insert into upvote (user_id,ans_id) values (?,?)";

	private final String SELECT_LAST_INSERT_UPVOTE = "select * from upvote where upvote_id = last_insert_id()";

	public Upvote queryIsUpvoteByAnsIdAndUserId(long ansID,long userID){
		try {
			Upvote upvote = jdbcOperations.queryForObject(
					QUERY_UPVOTE,
					new UpvoteRowMapper(),
					ansID,
					userID
			);
			return upvote;
		}
		catch (EmptyResultDataAccessException e){

			return addNewUpvoteRecord(ansID,userID);
		}

	}

	public Upvote addNewUpvoteRecord(long ansID,long userID){
		jdbcOperations.update(ADD_NEW_UPVOTE_RECORD,userID,ansID);
		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_UPVOTE,new UpvoteRowMapper());
	}

	public void updateRecordForUpvote(Upvote upvote){
		jdbcOperations.update(UPDATE_RECORD,upvote.isUp(),upvote.isDown(),upvote.getUpvoteID());
	}

	private class UpvoteRowMapper implements RowMapper<Upvote> {
		public Upvote mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Upvote(
					rs.getLong("upvote_id"),
					rs.getLong("user_id"),
					rs.getLong("ans_id"),
					rs.getBoolean("up"),
					rs.getBoolean("down"));
		}
	}
}
