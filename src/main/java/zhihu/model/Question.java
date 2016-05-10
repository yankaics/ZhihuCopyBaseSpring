package zhihu.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ZJ on 2016/4/13.
 */
@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ques_id")
	private long quesID;
	@Column(name="user_id")
	private long userID;
	@Column(name="views")
	private long views;
	@Column(name="ques_title")
	private String quesTitle;
	@Column(name="ques_content")
	private String quesContent;
	@Column(name="tags")
	private String tags;//逗号分隔
	@Column(name="create_at")
	private Timestamp createAt;

	public Question() {
	}
	public Question(long userID,String quesTitle, String quesContent) {

		this.userID = userID;
		this.quesTitle = quesTitle;
		this.quesContent = quesContent;
	}

	public Question(long quesID, long userID, long views,String quesTitle, String quesContent) {
		this.quesID = quesID;
		this.userID = userID;
		this.views = views;
		this.quesTitle = quesTitle;
		this.quesContent = quesContent;
	}

	public Question(long quesID, long userID, long views,String quesTitle, String quesContent,String tags,Timestamp createAt) {
		this.quesID = quesID;
		this.userID = userID;
		this.views = views;
		this.quesTitle = quesTitle;
		this.quesContent = quesContent;
		this.tags = tags;
		this.createAt = createAt;
	}
	public Question(long userID, long views,String quesTitle, String quesContent,String tags,Timestamp createAt) {
		this.userID = userID;
		this.views = views;
		this.quesTitle = quesTitle;
		this.quesContent = quesContent;
		this.tags = tags;
		this.createAt = createAt;
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

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
}
