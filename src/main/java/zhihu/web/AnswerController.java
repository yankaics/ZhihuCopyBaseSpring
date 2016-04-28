package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhihu.dao.AnswerDao;
import zhihu.dao.UpvoteDao;
import zhihu.model.Upvote;
import zhihu.security.CustomUserDetail;

import javax.servlet.http.HttpSession;

/**
 * Created by ZJ on 2016/4/28.
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {


	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private UpvoteDao upvoteDao;


	@RequestMapping(value = "/up")
	@ResponseBody
	public String up(@RequestParam("ans_id") long ans_id, HttpSession session){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");

		Upvote upvote = upvoteDao.queryUpvoteByAnsIdAndUserId(ans_id,user.getUserID());

		upvote.setUp(!upvote.isUp());
		if(upvote.isUp() && upvote.isDown()){
			upvote.setDown(!upvote.isDown());
		}

		answerDao.updateUpNumber(upvote.getAnsID(),upvote);
		upvoteDao.updateRecordForUpvote(upvote);
		return "success";
	}

	@RequestMapping(value = "/down")
	@ResponseBody
	public String down(@RequestParam("ans_id") long ans_id, HttpSession session){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");

		Upvote upvote = upvoteDao.queryUpvoteByAnsIdAndUserId(ans_id,user.getUserID());

		upvote.setDown(!upvote.isDown());
		if(upvote.isUp() && upvote.isDown()){
			upvote.setUp(!upvote.isUp());
			answerDao.updateUpNumber(upvote.getAnsID(),upvote);
		}

		upvoteDao.updateRecordForUpvote(upvote);
		return "success";
	}

}
