package zhihu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhihu.dao.AnswerDao;
import zhihu.model.Answer;

import java.util.List;

/**
 * Created by ZJ on 2016/5/10.
 */
@Service
public class AnswerService {

	@Autowired
	public AnswerDao answerDao;

	public Answer save(Answer answer){
		return answerDao.save(answer);
	}

	public Answer findByAnsID(long ansID){
		return answerDao.findByAnsID(ansID);
	}

	List<Answer> findAllByUserID(long userID){
		return answerDao.findAllByUserID(userID);
	}

	List<Answer> findAllByQuesID(long userID){
		return answerDao.findAllByQuesID(userID);
	}
}
