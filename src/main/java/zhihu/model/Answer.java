package zhihu.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ZJ on 2016/4/13.
 */
@Entity
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ans_id")
	private long ansID;

	@Column(name="user_id")
	private long userID;

	@Column(name="ques_id")
	private long quesID;

	@Column(name="ans_content")
	private String ansContent;

	@Column(name="upvote_number")
	private int upvoteNumber;

	@Column(name="create_at")
	private Timestamp createAt;

	public Answer(){

	}

	public Answer(long ansID, long userID,long quesID,String ansContent,int upvoteNumber) {
		this.ansID = ansID;
		this.userID = userID;
		this.quesID = quesID;
		this.ansContent = ansContent;
		this.upvoteNumber = upvoteNumber;
	}

	public Answer(long ansID, long userID, long quesID, String ansContent, int upvoteNumber, Question question, User user, Timestamp createAt) {
		this.ansID = ansID;
		this.userID = userID;
		this.quesID = quesID;
		this.ansContent = ansContent;
		this.upvoteNumber = upvoteNumber;
		this.createAt = createAt;
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

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
}
