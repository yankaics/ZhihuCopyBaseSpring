package zhihu.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import zhihu.dao.UserDao;
import zhihu.security.CustomUserDetail;
import zhihu.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJ on 2016/4/19.
 */
public class UserService implements UserDetailsService{


	private UserDao userDao;

	public UserService(UserDao userDao){
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findUserByUserName(username);
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			return new CustomUserDetail(
					user.getUsername(),
					user.getPassword(),
					authorities,
					user.getUserID());
		}
		throw new UsernameNotFoundException("账户不存在");
	}
}
