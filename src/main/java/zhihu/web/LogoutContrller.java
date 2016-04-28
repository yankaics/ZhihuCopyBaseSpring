package zhihu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutContrller {
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String logout(HttpSession session){
//		session.invalidate();
//		return "login";
//	}
}
