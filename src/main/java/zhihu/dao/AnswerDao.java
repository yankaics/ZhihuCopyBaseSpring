package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.Answer;
import zhihu.domain.Question;
import zhihu.domain.Upvote;
import zhihu.domain.User;

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
	@Autowired
	private UserDao userDao;
	@Autowired
	private UpvoteDao upvoteDao;

	private final String QUERY_ANSWER_BY_USER_ID ="select * from answer where user_id=? limit 10";

	private final  String QUERY_ANSWER_BY_QUES_ID ="select * from answer where ques_id=? limit 10";

	private final  String QUERY_ANSWER_BY_ANS_ID ="select * from answer where ans_id=?";

	private final String UPDATE_UPVOTE_PLUS_ONE = "UPDATE answer SET upvote_number = upvote_number+1 where ans_id=?";
	private final String UPDATE_UPVOTE_CUT_ONE = "UPDATE answer SET upvote_number = upvote_number-1 where ans_id=?";

	private final String ADD_NEW_ANSWER = "insert into answer (user_id,ques_id,ans_content) values (?,?,?)";

	private final String SELECT_LAST_INSERT_ANSWER = "select * from answer where ans_id = last_insert_id()";

	public Answer addNewAnswer(Answer answer){
		jdbcOperations.update(ADD_NEW_ANSWER,
				answer.getUserID(),
				answer.getQuesID(),
				answer.getAnsContent());
		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_ANSWER,
				new AnswerRowMapper());
	}


	public Answer findAnswersByAnsID(long ansID){
		try{
			Answer queryAnswers = jdbcOperations.queryForObject(
					QUERY_ANSWER_BY_ANS_ID,
					new AnswerDao.AnswerRowMapper(),
					ansID);
			return queryAnswers;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	public void updateUpNumber(long ansID, Upvote upvote){
		if(upvote.isUp())
			jdbcOperations.update(UPDATE_UPVOTE_PLUS_ONE,ansID);
		else
			jdbcOperations.update(UPDATE_UPVOTE_CUT_ONE,ansID);
	}

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


	public List<Answer> findAnswersByQuesID(long quesID,long current_user_id){
		try{
			List<Answer> queryAnswers = jdbcOperations.query(
					QUERY_ANSWER_BY_QUES_ID,
					new AnswerDao.AnswerRowMapper(current_user_id),
					quesID);
			return queryAnswers;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}


	private class AnswerRowMapper implements RowMapper<Answer> {

		private long currentUserId;

		public AnswerRowMapper(){

		}

		public AnswerRowMapper(long currentUserId){
			this.currentUserId = currentUserId;
		}

		public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Question question = questionDao.findOneQuestionByQueId(rs.getLong("ques_id"));
			User user = userDao.findUserByUserID(rs.getLong("user_id"));
			Upvote upvote = upvoteDao.queryIsUpvoteByAnsIdAndUserId(rs.getLong("ans_id"),currentUserId);
			Answer answer = new Answer(
								rs.getLong("ans_id"),
								rs.getLong("user_id"),
								rs.getLong("ques_id"),
								rs.getString("ans_content"),
								rs.getInt("upvote_number"),
								question,
								user);

			answer.setUpvote(upvote);

			return answer;
		}
	}
}
