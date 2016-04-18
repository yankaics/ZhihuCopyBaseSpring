package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.Question;
import zhihu.domain.Tags;

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

	@Autowired
	private TagsDao tagsDao;

	private final String FIND_QUESTION_BY_QUE_ID = "select * from question where ques_id = ?";

	private String QUERY_QUESTION_BY_USER_ID ="select * from question where user_id=? limit 10";

	private String ADD_NEW_QUESTION = "insert into question (ques_title,ques_content,user_id) values (?,?,?)";

	private final String SELECT_LAST_INSERT_QUESTION = "select * from question where ques_id = last_insert_id()";

	public Question addNewQuestion(Question question,String tagString){
		jdbcOperations.update(ADD_NEW_QUESTION,
				question.getQuesTitle(),
				question.getQuesContent(),
				question.getUserID());
		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_QUESTION,
				new QuestionRowMapper(tagString));
	}

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
		private String tagString;

		public QuestionRowMapper(){

		}
		public QuestionRowMapper(String tagString){
			this.tagString = tagString;
		}
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

			Tags tags = tagsDao.findTagsByQuesID(rs.getLong("ques_id"));
			if(tags==null)
				tags=tagsDao.addNewTags(rs.getLong("ques_id"),tagString);

			return new Question(
					rs.getLong("ques_id"),
					rs.getLong("user_id"),
					rs.getLong("views"),
					rs.getString("ques_title"),
					rs.getString("ques_content"),
					tags);
		}
	}
}
