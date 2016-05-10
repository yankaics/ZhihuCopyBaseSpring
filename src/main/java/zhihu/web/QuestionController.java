package zhihu.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhihu.model.Answer;
import zhihu.model.ContentWrapper;
import zhihu.model.Question;
import zhihu.model.QuestionForm;
import zhihu.security.CustomUserDetail;
import zhihu.service.ContentWrapperService;
import zhihu.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ZJ on 2016/4/15.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	ContentWrapperService contentWrapperService;

	private static final Logger logger = Logger.getLogger(QuestionController.class);

	@RequestMapping("/{ques_id}")
	public String question(@PathVariable long ques_id, Model model){
		Question question = questionService.findByQuesID(ques_id);
		model.addAttribute(question);
		return "question";
	}

	@RequestMapping("/{ques_id}/answers")
	@ResponseBody
	public List<ContentWrapper> getAnswers(@PathVariable long ques_id, HttpSession session) {
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		List<ContentWrapper> answers = contentWrapperService.findByQuesID(ques_id,user.getUserID());
		return answers;
	}



	@RequestMapping(value = "/{ques_id}/answerQuestion",method = RequestMethod.POST)
	@ResponseBody
	public ContentWrapper answerQuestion(@PathVariable long ques_id, HttpSession session, @RequestBody Answer answer){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");
		logger.debug(answer.getAnsContent());
		answer.setQuesID(ques_id);
		answer.setUserID(user.getUserID());
		return contentWrapperService.save(answer,user.getUserID());
	}

	@RequestMapping(value = "/ask",method = RequestMethod.POST)
	public String askQuestion(HttpSession session, QuestionForm questionForm){
		CustomUserDetail user = (CustomUserDetail) session.getAttribute("user");

		Question question =new Question(user.getUserID(),
							0,
							questionForm.getQuesTitle(),
							questionForm.getQuesContent(),
							questionForm.getTags(),
							new Timestamp(System.currentTimeMillis()));

		Question newQuestion = questionService.saveQuestion(question);
		return "redirect:/question/"+newQuestion.getQuesID();
	}
}
