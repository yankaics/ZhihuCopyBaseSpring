package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import zhihu.model.ContentWrapper;
import zhihu.model.Question;
import zhihu.security.CustomUserDetail;
import zhihu.service.ContentWrapperService;
import zhihu.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	ContentWrapperService contentWrapperService;

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = {"","/"})
	public String home(HttpSession session,Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<ContentWrapper> contents = contentWrapperService.findByUserID(user.getUserID(),user.getUserID());
		model.addAttribute(contents);
		model.addAttribute("active","home");
		return "home";
	}


	@RequestMapping(value = "/answers")
	public String getAnswers(HttpSession session, Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<ContentWrapper> contents = contentWrapperService.findByUserID(user.getUserID(),user.getUserID());
		model.addAttribute(contents);
		model.addAttribute("active","answers");
		return "home";
	}


	@RequestMapping(value = "/questions")
	public String getQustions(HttpSession session,Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Question> questions = questionService.findByUserID(user.getUserID());
		model.addAttribute(questions);
		model.addAttribute("active","questions");
		return "home";
	}

}
