package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zhihu.model.User;
import zhihu.security.CustomUserDetail;
import zhihu.service.UserService;
import zhihu.util.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String test(){
		return "test";
	}


	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public String signUp(@RequestParam("username") String username,
	                                   @RequestParam("password") String password,
	                                   HttpSession session){
		if(!username.isEmpty() && !password.isEmpty()){

			CustomUserDetail newUser = userService.signIn(new User(username, new MD5Util().encode(password)));
			session.setAttribute("user",newUser);
			return "index";
		}
		else
			return "error";
	}

	public void register(HttpServletRequest request,User newUser) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( newUser.getUsername(), newUser.getPassword());

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}
}
