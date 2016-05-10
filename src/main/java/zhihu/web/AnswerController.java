package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zhihu.model.Upvote;
import zhihu.security.CustomUserDetail;
import zhihu.service.ContentWrapperService;
import zhihu.service.UpvoteService;

import javax.servlet.http.HttpSession;

/**
 * Created by ZJ on 2016/4/28.
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {


	@Autowired
	private ContentWrapperService contentWrapperService;

	@Autowired
	private UpvoteService upvoteService;


	@RequestMapping(value = "/up")
	@ResponseBody
	public String up(@RequestParam("ans_id") long ans_id, HttpSession session){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");

		Upvote upvote = upvoteService.findByAnsIDAndUserID(ans_id,user.getUserID());

		upvote.setUp(!upvote.isUp());
		if(upvote.isUp() && upvote.isDown()){
			upvote.setDown(!upvote.isDown());
		}

		contentWrapperService.updateUpNumber(upvote.getAnsID(),upvote);
		upvoteService.updateUpvote(upvote);
		return "success";
	}

	@RequestMapping(value = "/down")
	@ResponseBody
	public String down(@RequestParam("ans_id") long ans_id, HttpSession session){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");

		Upvote upvote = upvoteService.findByAnsIDAndUserID(ans_id,user.getUserID());

		upvote.setDown(!upvote.isDown());
		if(upvote.isUp() && upvote.isDown()){
			upvote.setUp(!upvote.isUp());
			contentWrapperService.updateUpNumber(upvote.getAnsID(),upvote);
		}

		upvoteService.updateUpvote(upvote);
		return "success";
	}

}
