package zhihu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhihu.dao.QuestionDao;
import zhihu.model.Question;

import java.util.List;

/**
 * Created by ZJ on 2016/5/10.
 */
@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public Question saveQuestion(Question question){
		return questionDao.save(question);
	}

	public Question findByQuesID(long quesId){
		return questionDao.findByQuesID(quesId);
	}

	public List<Question> findByUserID(long userId){
		return questionDao.findByUserID(userId);
	}

}

