package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zhihu.dao.AnswerDao;
import zhihu.model.Answer;
import zhihu.security.CustomUserDetail;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = {"/","index"})
public class IndexController {

	@Autowired
	AnswerDao answerDao;

	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpSession session, Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Answer> answers = answerDao.findLatestAnswers(user.getUserID());
		model.addAttribute(answers);
		return "index";
	}


//	@RequestMapping(value = "/latestAnswers",method = RequestMethod.GET)
//	@ResponseBody
//	public List<Answer> getLatestAnswers(HttpSession session){
//		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
//		List<Answer> answers = answerDao.findLatestAnswers(user.getUserID());
//		return answers;
//	}

}
