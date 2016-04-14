package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhihu.dao.AnswerDao;
import zhihu.dao.QuestionDao;
import zhihu.domain.Answer;
import zhihu.domain.Question;
import zhihu.domain.User;

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
	public String home(HttpSession session, Model model){
		User user = (User) session.getAttribute("user");
		if (user==null)
			return "redirect:/login";
		return "home";
	}


	@RequestMapping(value = "/answers",method = RequestMethod.GET)
	@ResponseBody
	public List<Answer> getAnswers(HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Answer> answers = answerDao.findAnswersByUserID(user.getUserID());
		return answers;
	}

	@RequestMapping(value = "/question",method = RequestMethod.GET)
	@ResponseBody
	public List<Question> getQustions(HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Question> questions = questionDao.findQuestionsByUserID(user.getUserID());
		return questions;
	}
}
