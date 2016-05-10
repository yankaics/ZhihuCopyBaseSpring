package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zhihu.dao.UserTestDao;
import zhihu.model.ContentWrapper;
import zhihu.model.User;
import zhihu.security.CustomUserDetail;
import zhihu.service.ContentWrapperService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = {"/","index"})
public class IndexController {

	@Autowired
	ContentWrapperService contentWrapperService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpSession session, Model model){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<ContentWrapper> contents = contentWrapperService.findByUserID(user.getUserID(),user.getUserID());
		model.addAttribute(contents);
		return "index";
	}

	@Autowired
	UserTestDao userTestDao;

	@RequestMapping(value = "/test")
	public String test(){
		User test = new User();
		test.setUsername("test");
		test.setPassword("123456");
		userTestDao.save(test);
		//test.setPassword("123");
		return "test";
	}

//	@RequestMapping(value = "/latestAnswers",method = RequestMethod.GET)
//	@ResponseBody
//	public List<Answer> getLatestAnswers(HttpSession session){
//		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
//		List<Answer> answers = answerDao.findLatestAnswers(user.getUserID());
//		return answers;
//	}

}
