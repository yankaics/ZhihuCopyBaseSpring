package zhihu.domain;

/**
 * Created by ZJ on 2016/4/13.
 */
public class Answer {
	private long ansID;
	private long userID;
	private long quesID;
	private String ansContent;
	private int upvoteNumber;
	private Question question;
	private User user;
	private Upvote upvote;

	public Answer(long ansID, long userID,long quesID,String ansContent,int upvoteNumber) {
		this.ansID = ansID;
		this.userID = userID;
		this.quesID = quesID;
		this.ansContent = ansContent;
		this.upvoteNumber = upvoteNumber;
	}

	public Answer(long ansID, long userID, long quesID, String ansContent, int upvoteNumber, Question question, User user) {
		this.ansID = ansID;
		this.userID = userID;
		this.quesID = quesID;
		this.ansContent = ansContent;
		this.upvoteNumber = upvoteNumber;
		this.question = question;
		this.user = user;
	}


	public long getAnsID() {
		return ansID;
	}

	public void setAnsID(long ansID) {
		this.ansID = ansID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getAnsContent() {
		return ansContent;
	}

	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}

	public long getQuesID() {
		return quesID;
	}

	public void setQuesID(long quesID) {
		this.quesID = quesID;
	}

	public int getUpvoteNumber() {
		return upvoteNumber;
	}

	public void setUpvoteNumber(int upvoteNumber) {
		this.upvoteNumber = upvoteNumber;
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
