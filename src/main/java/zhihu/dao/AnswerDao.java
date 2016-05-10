package zhihu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zhihu.model.Answer;

import java.util.List;

/**
 * Created by ZJ on 2016/4/14.
 */
@Repository
@Transactional
public interface AnswerDao extends JpaRepository<Answer,Long> {

	Answer save(Answer answer);
	Answer findByAnsID(long ansID);
	List<Answer> findAllByUserID(long userID);
	List<Answer> findAllByQuesID(long quesID);

//	@Autowired
//	private JdbcOperations jdbcOperations;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private QuestionService questionService;
//
//	@Autowired
//	private UpvoteService upvoteService;
//
//	private final String QUERY_ANSWER_BY_USER_ID ="select * from answer where user_id=? ORDER BY create_at desc limit 20";
//
//	private final  String QUERY_ANSWER_BY_QUES_ID ="select * from answer where ques_id=? limit 10";
//
//	private final  String QUERY_ANSWER_BY_ANS_ID ="select * from answer where ans_id=?";
//
//	private final String UPDATE_UPVOTE_PLUS_ONE = "UPDATE answer SET upvote_number = upvote_number+1 where ans_id=?";
//	private final String UPDATE_UPVOTE_CUT_ONE = "UPDATE answer SET upvote_number = upvote_number-1 where ans_id=?";
//
//	private final String ADD_NEW_ANSWER = "insert into answer (user_id,ques_id,ans_content,create_at) values (?,?,?,NOW())";
//
//	private final String SELECT_LAST_INSERT_ANSWER = "select * from answer where ans_id = last_insert_id()";
//
//	private final String SELECT_LATEST_ANSWERS = "select * from answer ORDER BY create_at desc ";
//
//	public List<Answer> findLatestAnswers(long currentUserId) {
//		try{
//			List<Answer> queryAnswers = jdbcOperations.query(
//					SELECT_LATEST_ANSWERS,
//					new AnswerDao.AnswerRowMapper(currentUserId));
//			return queryAnswers;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//	}
//
//	public Answer addNewAnswer(Answer answer){
//		jdbcOperations.update(ADD_NEW_ANSWER,
//				answer.getUserID(),
//				answer.getQuesID(),
//				answer.getAnsContent());
//		return jdbcOperations.queryForObject(SELECT_LAST_INSERT_ANSWER,
//				new AnswerRowMapper());
//	}
//
//
//	public Answer findAnswersByAnsID(long ansID){
//		try{
//			Answer queryAnswers = jdbcOperations.queryForObject(
//					QUERY_ANSWER_BY_ANS_ID,
//					new AnswerDao.AnswerRowMapper(),
//					ansID);
//			return queryAnswers;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//	}
//
//	public void updateUpNumber(long ansID, Upvote upvote){
//		if(upvote.isUp())
//			jdbcOperations.update(UPDATE_UPVOTE_PLUS_ONE,ansID);
//		else
//			jdbcOperations.update(UPDATE_UPVOTE_CUT_ONE,ansID);
//	}
//
//	public List<Answer> findAnswersByUserID(long userID){
//		try{
//			List<Answer> queryAnswers = jdbcOperations.query(
//					QUERY_ANSWER_BY_USER_ID,
//					new AnswerDao.AnswerRowMapper(userID),
//					userID);
//			return queryAnswers;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//	}
//
//
//	public List<Answer> findAnswersByQuesID(long quesID,long current_user_id){
//		try{
//			List<Answer> queryAnswers = jdbcOperations.query(
//					QUERY_ANSWER_BY_QUES_ID,
//					new AnswerDao.AnswerRowMapper(current_user_id),
//					quesID);
//			return queryAnswers;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//	}
//
//
//	private class AnswerRowMapper implements RowMapper<Answer> {
//
//		private long currentUserId;
//
//		public AnswerRowMapper(){
//
//		}
//
//		public AnswerRowMapper(long currentUserId){
//			this.currentUserId = currentUserId;
//		}
//
//		public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Question question = questionService.findAllByQuesID(rs.getLong("ques_id"));
//			User user = userService.findAllByUserID(rs.getLong("user_id"));
//			Upvote upvote = upvoteService.findByAnsIDAndUserID(rs.getLong("ans_id"),currentUserId);
//			Answer answer = new Answer(
//								rs.getLong("ans_id"),
//								rs.getLong("user_id"),
//								rs.getLong("ques_id"),
//								rs.getString("ans_content"),
//								rs.getInt("upvote_number"),
//								question,
//								user,
//								rs.getTimestamp("create_at"));
//
//			answer.setUpvote(upvote);
//
//			return answer;
//		}
//	}
}
