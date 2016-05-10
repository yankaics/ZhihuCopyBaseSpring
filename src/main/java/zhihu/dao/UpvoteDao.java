package zhihu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zhihu.model.Upvote;

/**
 * Created by ZJ on 2016/4/17.
 */

@Repository
@Transactional
public interface UpvoteDao extends JpaRepository<Upvote,Long> {

	Upvote save(Upvote upvote);
	Upvote findByAnsIDAndUserID(long ansId,long userId);

//	@Autowired
//	JdbcOperations jdbcOperations;
//
//	private final String QUERY_UPVOTE = "select * from upvote where ans_id = ? and user_id =?";
//
//	private final String UPDATE_RECORD = "UPDATE upvote SET up = ?, down = ? where upvote_id = ?";
//
//	private final String ADD_NEW_UPVOTE_RECORD = "insert into upvote (user_id,ans_id) values (?,?)";
//
//	private final String SELECT_LAST_INSERT_UPVOTE = "select * from upvote where upvote_id = last_insert_id()";
//
//
//	public Upvote queryUpvoteByAnsIdAndUserId(long ansID, long userID){
//		try {
//			Upvote upvote = jdbcOperations.queryForObject(
//					QUERY_UPVOTE,
//					new UpvoteRowMapper(),
//					ansID,
//					userID
//			);
//			return upvote;
//		}
//		catch (EmptyResultDataAccessException e){
//
//			return addNewUpvoteRecord(ansID,userID);
//		}
//
//	}
//
//	public Upvote addNewUpvoteRecord(long ansID,long userID){
//		jdbcOperations.update(ADD_NEW_UPVOTE_RECORD,userID,ansID);
//		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_UPVOTE,new UpvoteRowMapper());
//	}
//
//	public void updateRecordForUpvote(Upvote upvote){
//		jdbcOperations.update(UPDATE_RECORD,upvote.isUp(),upvote.isDown(),upvote.getUpvoteID());
//	}
//
//	private class UpvoteRowMapper implements RowMapper<Upvote> {
//		public Upvote mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//			return new Upvote(
//					rs.getLong("upvote_id"),
//					rs.getLong("user_id"),
//					rs.getLong("ans_id"),
//					rs.getBoolean("up"),
//					rs.getBoolean("down"));
//		}
//	}
}
