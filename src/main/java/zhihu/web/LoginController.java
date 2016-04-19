package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhihu.dao.UserDao;
import zhihu.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserDao userDao;


	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String test(){
		return "test";
	}


	@RequestMapping(value = "/signup",method = RequestMethod.POST,consumes = {"application/json;charset=UTF-8"}, produces={"application/json;charset=UTF-8"})
	public @ResponseBody String signUp(@RequestBody User user, HttpSession session){
		if(!user.getUsername().isEmpty() && !user.getPassword().isEmpty()){
			User newuser = userDao.registerNewUser(user);
			session.setAttribute("user",newuser);
			return "success";
		}
		else {
			return "username or password is empty";
		}
	}

//	@RequestMapping(value = "/signin",method = RequestMethod.POST)
//	public @ResponseBody String signIn(@RequestBody User user, HttpSession session){
//		if(!user.getUsername().isEmpty() && !user.getPassword().isEmpty()){
//			return login(user,session);
//		}
//		return "username or password is empty";
//	}
//
//	public String login(User user, HttpSession session){
//		User queryUser = userDao.findUserByUserName(user.getUsername());
//
//		if (queryUser==null)
//			return "不存在该账户";
//
//		if(queryUser.getPassword().equals(new MD5Util().encode(user.getPassword()))){
//			session.setAttribute("user",queryUser);
//			return "success";
//		}
//		else {
//			return "密码错误";
//		}
//
//	}
}
