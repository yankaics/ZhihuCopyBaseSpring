package zhihu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhihu.dao.AnswerDao;
import zhihu.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJ on 2016/5/10.
 */

@Service
public class ContentWrapperService {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;

	@Autowired
	private UpvoteService upvoteService;


	public ContentWrapper save(Answer answer, long currentUserId){
		Answer newAnswer = answerService.save(answer);
		return wrapAnswer(newAnswer,currentUserId);
	}

	public List<ContentWrapper> findByUserID(long userID, long currentUserId){
		List<Answer> answers= answerService.findAllByUserID(userID);
		List<ContentWrapper> contentWrappers = new ArrayList<>();
		for (Answer answer :answers){
			ContentWrapper contentWrapper = wrapAnswer(answer, currentUserId);
			contentWrappers.add(contentWrapper);
		}
		return contentWrappers;
	}

	public List<ContentWrapper> findByQuesID(long quesID, long currentUserId){
		List<Answer> answers= answerService.findAllByQuesID(quesID);
		List<ContentWrapper> contentWrappers = new ArrayList<>();
		for (Answer answer :answers){
			ContentWrapper contentWrapper = wrapAnswer(answer, currentUserId);
			contentWrappers.add(contentWrapper);
		}

		return contentWrappers;
	}

	public void updateUpNumber(long ansID, Upvote upvote){

		Answer answer = answerService.findByAnsID(ansID);

		if(upvote.isUp())
			answer.setUpvoteNumber(answer.getUpvoteNumber()+1);
		else
			answer.setUpvoteNumber(answer.getUpvoteNumber()-1);

		answerService.save(answer);
	}

	public ContentWrapper wrapAnswer(Answer answer, long currentUserId){
		Question question = questionService.findByQuesID(answer.getQuesID());
		User user = userService.findByUserID(answer.getUserID());
		Upvote upvote = upvoteService.findByAnsIDAndUserID(answer.getAnsID(),currentUserId);
		return  new ContentWrapper(question,answer,user,upvote);
	}
}
