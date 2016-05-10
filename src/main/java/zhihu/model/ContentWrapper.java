package zhihu.model;

/**
 * Created by ZJ on 2016/5/10.
 */
public class ContentWrapper {
	private Answer answer;
	private Question question;
	private User user;
	private Upvote upvote;

	public ContentWrapper(){

	}

	public ContentWrapper(Question question, Answer answer, User user, Upvote upvote) {
		this.question = question;
		this.answer = answer;
		this.user = user;
		this.upvote = upvote;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Upvote getUpvote() {
		return upvote;
	}

	public void setUpvote(Upvote upvote) {
		this.upvote = upvote;
	}
}
