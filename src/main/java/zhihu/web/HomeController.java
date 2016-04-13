package zhihu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ZJ on 2016/4/13.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(){
		return "home";
	}
}
