package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.Tags;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZJ on 2016/4/16.
 */

@Repository
public class TagsDao {

	@Autowired
	JdbcOperations jdbcOperations;
	private final String QUERY_TAGS_BY_QUES_ID = "select * from tags where ques_id = ?";


	public Tags findTagsByQuesID(long ques_id){
		try{
			Tags tags = jdbcOperations.queryForObject(
					QUERY_TAGS_BY_QUES_ID,
					new TagsRowMapper(),
					ques_id
			);
			return tags;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	private class TagsRowMapper implements RowMapper<Tags> {
		public Tags mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Tags(
					rs.getLong("tags_id"),
					rs.getLong("ques_id"),
					rs.getString("tag1"),
					rs.getString("tag2"),
					rs.getString("tag3"));
		}
	}
}