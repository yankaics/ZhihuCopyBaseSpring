package zhihu.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by ZJ on 2016/4/19.
 */
public class CustomUserDetail extends org.springframework.security.core.userdetails.User {
	private long userID;


	public CustomUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities,long userID) {
		super(username, password, authorities);
		this.userID=userID;
	}
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
}
