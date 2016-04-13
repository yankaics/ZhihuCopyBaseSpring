package zhihu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhihu.domain.User;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "login";
	}


	@RequestMapping(value = "/signup",method = RequestMethod.POST,consumes = {"application/json;charset=UTF-8"}, produces={"application/json;charset=UTF-8"})
	public @ResponseBody String signUp(@RequestBody User user){
		String username = user.getUsername();
		System.out.println(username);
		return "success";
	}

	@RequestMapping(value = "/signin",method = RequestMethod.POST)
	public String signIn(User user){
		return "login";
	}
}
