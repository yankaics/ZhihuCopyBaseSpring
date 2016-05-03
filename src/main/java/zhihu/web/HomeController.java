package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import zhihu.dao.AnswerDao;
import zhihu.dao.QuestionDao;
import zhihu.model.Answer;
import zhihu.model.Question;
import zhihu.model.User;
import zhihu.security.CustomUserDetail;
import zhihu.service.UserTestService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	AnswerDao answerDao;

	@Autowired
	QuestionDao questionDao;

	@RequestMapping(value = {"","/"})
	public String home(HttpSession session,Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Answer> answers = answerDao.findAnswersByUserID(user.getUserID());
		model.addAttribute(answers);
		model.addAttribute("active","home");
		return "home";
	}


	@RequestMapping(value = "/answers")
	public String getAnswers(HttpSession session, Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Answer> answers = answerDao.findAnswersByUserID(user.getUserID());
		model.addAttribute(answers);
		model.addAttribute("active","answers");
		return "home";
	}


	@RequestMapping(value = "/questions")
	public String getQustions(HttpSession session,Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Question> questions = questionDao.findQuestionsByUserID(user.getUserID());
		model.addAttribute(questions);
		model.addAttribute("active","questions");
		return "home";
	}


	@Autowired
	UserTestService userTestDao;

	@RequestMapping(value = "/test")
	public String test(){
		User test = new User();
		test.setUsername("test");
		test.setPassword("123456");
		userTestDao.addUser(test);
		return "test";
	}

}
