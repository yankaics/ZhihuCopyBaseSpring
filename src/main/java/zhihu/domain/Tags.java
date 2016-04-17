package zhihu.domain;

/**
 * Created by ZJ on 2016/4/16.
 */
public class Tags {
	private long tags_id;
	private long ques_id;
	private String tag1;
	private String tag2;
	private String tag3;

	public Tags(long tags_id, long ques_id, String tag1, String tag2, String tag3) {
		this.tags_id = tags_id;
		this.ques_id = ques_id;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
	}

	public long getTags_id() {
		return tags_id;
	}

	public void setTags_id(long tags_id) {
		this.tags_id = tags_id;
	}

	public long getQues_id() {
		return ques_id;
	}

	public void setQues_id(long ques_id) {
		this.ques_id = ques_id;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
}
