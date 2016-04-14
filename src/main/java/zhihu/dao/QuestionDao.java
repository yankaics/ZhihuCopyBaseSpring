package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZJ on 2016/4/14.
 */
@Repository
public class QuestionDao {
	@Autowired
	private JdbcOperations jdbcOperations;

	private final String FIND_QUESTION_BY_QUE_ID = "select * from question where ques_id = ?";

	public String QUERY_QUESTION_BY_USER_ID ="select * from question where user_id=? limit 10";

	public Question findOneQuestionByQueId(long quesID){
		try {
			Question question = jdbcOperations.queryForObject(
					FIND_QUESTION_BY_QUE_ID,
					new QuestionRowMapper(),
					quesID);
			return question;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	public List<Question> findQuestionsByUserID(long userID){
		try{
			List<Question> queryQuestions = jdbcOperations.query(
					QUERY_QUESTION_BY_USER_ID,
					new QuestionDao.QuestionRowMapper(),
					userID);
			return queryQuestions;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	private static class QuestionRowMapper implements RowMapper<Question> {
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Question(
					rs.getLong("ques_id"),
					rs.getLong("user_id"),
					rs.getLong("views"),
					rs.getString("ques_title"),
					rs.getString("ques_content"));
		}
	}
}
