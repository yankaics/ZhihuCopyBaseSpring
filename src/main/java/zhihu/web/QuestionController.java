package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhihu.dao.AnswerDao;
import zhihu.dao.QuestionDao;
import zhihu.dao.UpvoteDao;
import zhihu.domain.*;
import zhihu.security.CustomUserDetail;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ZJ on 2016/4/15.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionDao questionDao;

	@Autowired
	AnswerDao answerDao;

	@Autowired
	UpvoteDao upvoteDao;

	@RequestMapping("/{ques_id}")
	public String question(@PathVariable long ques_id, Model model){
		Question question = questionDao.findOneQuestionByQueId(ques_id);
		model.addAttribute(question);
		return "question";
	}

	@RequestMapping("/{ques_id}/answers")
	@ResponseBody
	public List<Answer> getAnswers(@PathVariable long ques_id, HttpSession session) {
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<Answer> answers = answerDao.findAnswersByQuesID(ques_id,user.getUserID());
		return answers;
	}

	@RequestMapping(value = "/up",method = RequestMethod.POST)
	@ResponseBody
	public String up(@RequestBody Upvote upvote){
		upvote.setUp(!upvote.isUp());
		if(upvote.isUp() && upvote.isDown()){
			upvote.setDown(!upvote.isDown());
		}

		answerDao.updateUpNumber(upvote.getAnsID(),upvote);
		upvoteDao.updateRecordForUpvote(upvote);
		return "success";
	}

	@RequestMapping(value = "/down",method = RequestMethod.POST)
	@ResponseBody
	public String down(@RequestBody Upvote upvote){
		upvote.setDown(!upvote.isDown());
		if(upvote.isUp() && upvote.isDown()){
			upvote.setUp(!upvote.isUp());
			answerDao.updateUpNumber(upvote.getAnsID(),upvote);
		}

		upvoteDao.updateRecordForUpvote(upvote);
		return "success";
	}


	@RequestMapping(value = "/{ques_id}/answerQuestion",method = RequestMethod.POST)
	@ResponseBody
	public Answer answerQuestion(@PathVariable long ques_id, HttpSession session,@RequestBody Answer answer){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		answer.setQuesID(ques_id);
		answer.setUserID(user.getUserID());
		return answerDao.addNewAnswer(answer);
	}

	@RequestMapping(value = "/ask",method = RequestMethod.POST)
	public String askQuestion(HttpSession session, QuestionForm questionForm){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		Question newQuestion = questionDao.addNewQuestion(user.getUserID(),questionForm.getQuesTitle(),
				questionForm.getQuesContent(),questionForm.getTags());
		return "redirect:/question/"+newQuestion.getQuesID();
	}
}
