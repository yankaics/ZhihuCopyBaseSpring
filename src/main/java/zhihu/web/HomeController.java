package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhihu.dao.AnswerDao;
import zhihu.dao.QuestionDao;
import zhihu.dao.UserTestDao;
import zhihu.model.Answer;
import zhihu.model.User;
import zhihu.security.CustomUserDetail;
import zhihu.model.Question;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
public class HomeController {

	@Autowired
	AnswerDao answerDao;

	@Autowired
	QuestionDao questionDao;

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(){
		return "home";
	}


	@RequestMapping(value = "/answers",method = RequestMethod.GET)
	@ResponseBody
	public List<Answer> getAnswers(HttpSession session){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Answer> answers = answerDao.findAnswersByUserID(user.getUserID());
		return answers;
	}


	@RequestMapping(value = "/question",method = RequestMethod.GET)
	@ResponseBody
	public List<Question> getQustions(HttpSession session){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Question> questions = questionDao.findQuestionsByUserID(user.getUserID());
		return questions;
	}


	@Autowired
	UserTestDao userTestDao;

	@RequestMapping(value = "/test")
	public String test(){
		User test = new User();
		test.setUsername("test");
		test.setPassword("123456");
		userTestDao.addUser(test);
		return "test";
	}

}
