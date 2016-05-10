package zhihu.model;

import javax.persistence.*;

/**
 * Created by ZJ on 2016/4/17.
 */
@Entity
@Table(name = "upvote")
public class Upvote {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="upvote_id")
	private long upvoteID;

	@Column(name="user_id")
	private long userID;

	@Column(name="ans_id")
	private long ansID;

	@Column(name="up")
	private boolean up;

	@Column(name="down")
	private boolean down;

	public Upvote(){

	}

	public Upvote(long upvoteID, long userID, long ansID,boolean up,boolean down) {
		this.upvoteID = upvoteID;
		this.userID = userID;
		this.ansID = ansID;
		this.up = up;
		this.down = down;
	}

	public Upvote(long userID, long ansID) {

		this.userID = userID;
		this.ansID = ansID;
	}

	public long getUpvoteID() {
		return upvoteID;
	}

	public void setUpvoteID(long upvoteID) {
		this.upvoteID = upvoteID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getAnsID() {
		return ansID;
	}

	public void setAnsID(long ansID) {
		this.ansID = ansID;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}


