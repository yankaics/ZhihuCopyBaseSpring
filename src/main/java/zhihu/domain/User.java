package zhihu.domain;

/**
 * Created by ZJ on 2016/4/13.
 */
public class User {
	private long userID;
	private String username;
	private String password;

	public User(){

	}

	public User(long userID, String username, String password) {
		this.userID = userID;
		this.username = username;
		this.password = password;
	}


	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
