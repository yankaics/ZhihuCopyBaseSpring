package zhihu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import zhihu.dao.AnswerDao;
import zhihu.dao.QuestionDao;
import zhihu.domain.Answer;
import zhihu.domain.Question;

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

	@RequestMapping("/{ques_id}")
	public String question(@PathVariable long ques_id, Model model){
		Question question = questionDao.findOneQuestionByQueId(ques_id);
		List<Answer> answers = answerDao.findAnswersByQuesID(ques_id);
		model.addAttribute(answers);
		model.addAttribute(question);
		return "question";
	}
}
