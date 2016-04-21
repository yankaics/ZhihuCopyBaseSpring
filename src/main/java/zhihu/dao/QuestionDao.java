package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	private String QUERY_QUESTION_BY_USER_ID ="select * from question where user_id=? limit 10";

	private String ADD_NEW_QUESTION = "insert into question (ques_title,ques_content,user_id,tags,create_at) values (?,?,?,?,NOW())";

	private final String SELECT_LAST_INSERT_QUESTION = "select * from question where ques_id = last_insert_id()";

	@CachePut(value = "questionCache",key = "#result.quesID")
	public Question addNewQuestion(long userID, String quesTitle, String quesContent, String tags){
		jdbcOperations.update(ADD_NEW_QUESTION,
				quesTitle,
				quesContent,
				userID,
				tags);
		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_QUESTION,
				new QuestionRowMapper());
	}

	@Cacheable(value = "questionCache",key = "#quesID")
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

	private class QuestionRowMapper implements RowMapper<Question> {

		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Question(
					rs.getLong("ques_id"),
					rs.getLong("user_id"),
					rs.getLong("views"),
					rs.getString("ques_title"),
					rs.getString("ques_content"),
					rs.getString("tags").split(","),
					rs.getTimestamp("create_at"));
		}
	}
}
