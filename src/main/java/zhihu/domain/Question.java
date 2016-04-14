package zhihu.domain;

/**
 * Created by ZJ on 2016/4/13.
 */
public class Question {
	private long quesID;
	private long userID;
	private String quesTitle;
	private String quesContent;

	public Question() {
	}

	public Question(long quesID, long userID, String quesTitle, String quesContent) {
		this.quesID = quesID;
		this.userID = userID;
		this.quesTitle = quesTitle;
		this.quesContent = quesContent;
	}

	public long getQuesID() {
		return quesID;
	}

	public void setQuesID(long quesID) {
		this.quesID = quesID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getQuesTitle() {
		return quesTitle;
	}

	public void setQuesTitle(String quesTitle) {
		this.quesTitle = quesTitle;
	}

	public String getQuesContent() {
		return quesContent;
	}

	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
}
