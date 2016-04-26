package zhihu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zhihu.dao.UserDao;
import zhihu.model.User;
import zhihu.security.CustomUserDetail;

import java.util.Collections;

/**
 * Created by ZJ on 2016/4/19.
 */
@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findUserByUserName(username);
		if (user != null) {

			return new CustomUserDetail(
					user.getUsername(),
					user.getPassword(),
					Collections.singleton(createAuthority()),
					user.getUserID());
		}
		throw new UsernameNotFoundException("账户不存在");
	}

	public CustomUserDetail signIn(User user) {
		User newUser = userDao.registerNewUser(user);

		CustomUserDetail customUserDetail =
				new CustomUserDetail(
						newUser.getUsername(), newUser.getPassword(),
						Collections.singleton(createAuthority()), newUser.getUserID());

		SecurityContextHolder.getContext().setAuthentication(authenticate(customUserDetail));

		return customUserDetail;
	}

	private Authentication authenticate(CustomUserDetail newUser) {
		return new UsernamePasswordAuthenticationToken(newUser, null, Collections.singleton(createAuthority()));
}

	private GrantedAuthority createAuthority() {
		return new SimpleGrantedAuthority("ROLE_USER");
	}
}
