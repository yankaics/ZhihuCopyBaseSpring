package zhihu.domain;

/**
 * Created by ZJ on 2016/4/13.
 */
public class Answer {
	private long ansID;
	private long userID;
	private long quesID;
	private String ansContent;
	private int upvote;

	public Answer(long ansID, long userID,long quesID,String ansContent,int upvote) {
		this.ansID = ansID;
		this.userID = userID;
		this.quesID = quesID;
		this.ansContent = ansContent;
		this.upvote = upvote;
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

	public int getUpvote() {
		return upvote;
	}

	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
}
