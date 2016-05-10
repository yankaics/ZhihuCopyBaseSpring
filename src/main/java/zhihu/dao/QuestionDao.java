package zhihu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zhihu.model.Question;

import java.util.List;

/**
 * Created by ZJ on 2016/4/14.
 */
@Repository
@Transactional
public interface QuestionDao extends JpaRepository<Question,Long> {


	Question save(Question question);

	Question findByQuesID(long quesID);

	List<Question> findByUserID(long userID);

//	@CachePut(value = "questionCache",key = "#result.quesID")
//	public Question addNewQuestion(long userID, String quesTitle, String quesContent, String tags){
//		jdbcOperations.update(ADD_NEW_QUESTION,
//				quesTitle,
//				quesContent,
//				userID,
//				tags);
//		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_QUESTION,
//				new QuestionRowMapper());
//	}
//
//	@Cacheable(value = "questionCache",key = "#quesID")
//	public Question findOneQuestionByQueId(long quesID){
//		try {
//			Question question = jdbcOperations.queryForObject(
//					FIND_QUESTION_BY_QUE_ID,
//					new QuestionRowMapper(),
//					quesID);
//			return question;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//	}
//
//	public List<Question> findQuestionsByUserID(long userID){
//		try{
//			List<Question> queryQuestions = jdbcOperations.query(
//					QUERY_QUESTION_BY_USER_ID,
//					new QuestionDao.QuestionRowMapper(),
//					userID);
//			return queryQuestions;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//	}

//	private class QuestionRowMapper implements RowMapper<Question> {
//
//		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//			return new Question(
//					rs.getLong("ques_id"),
//					rs.getLong("user_id"),
//					rs.getLong("views"),
//					rs.getString("ques_title"),
//					rs.getString("ques_content"),
//					rs.getString("tags").split(","),
//					rs.getTimestamp("create_at"));
//		}
//	}
}
