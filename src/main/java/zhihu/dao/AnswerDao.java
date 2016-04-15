package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.Answer;
import zhihu.domain.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZJ on 2016/4/14.
 */
@Repository
public class AnswerDao {
	@Autowired
	private JdbcOperations jdbcOperations;
	@Autowired
	private QuestionDao questionDao;

	public String QUERY_ANSWER_BY_USER_ID ="select * from answer where user_id=? limit 10";

	public String QUERY_ANSWER_BY_QUES_ID ="select * from answer where ques_id=? limit 10";

	public List<Answer> findAnswersByUserID(long userID){
		try{
			List<Answer> queryAnswers = jdbcOperations.query(
					QUERY_ANSWER_BY_USER_ID,
					new AnswerDao.AnswerRowMapper(),
					userID);
			return queryAnswers;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	public List<Answer> findAnswersByQuesID(long quesID){
		try{
			List<Answer> queryAnswers = jdbcOperations.query(
					QUERY_ANSWER_BY_QUES_ID,
					new AnswerDao.AnswerRowMapper(),
					quesID);
			return queryAnswers;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}


	private class AnswerRowMapper implements RowMapper<Answer> {

		public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Question question = questionDao.findOneQuestionByQueId(rs.getLong("ques_id"));
			return new Answer(
					rs.getLong("ans_id"),
					rs.getLong("user_id"),
					rs.getLong("ques_id"),
					rs.getString("ans_content"),
					rs.getInt("upvote"),
					question);
		}
	}
}
