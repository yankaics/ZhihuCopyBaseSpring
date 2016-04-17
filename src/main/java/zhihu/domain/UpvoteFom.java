package zhihu.domain;

/**
 * Created by ZJ on 2016/4/16.
 */
public class UpvoteFom {

	private long ans_id;
	private boolean upOrDown;

	public long getAns_id() {
		return ans_id;
	}

	public boolean isUpOrDown() {
		return upOrDown;
	}

	public void setUpOrDown(boolean upOrDown) {
		this.upOrDown = upOrDown;
	}

	public void setAns_id(long ans_id) {
		this.ans_id = ans_id;
	}
}
