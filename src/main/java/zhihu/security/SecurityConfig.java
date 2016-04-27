package zhihu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import zhihu.service.UserService;
import zhihu.util.MD5Util;

/**
 * Created by ZJ on 2016/4/18.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;


	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(new MD5Util());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/index","home").access("hasRole('USER')")
				.and()
					.formLogin()
					.loginPage("/login")
					.failureUrl("/login#/signin?error=1")
					.successHandler(new LoginSuccessHandler())
					.usernameParameter("username").passwordParameter("password")
				.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login#/signin?logout=1")
				.and()
					.exceptionHandling().accessDeniedPage("/Access_Denied")
					.and()
					.csrf().disable();

	}

}
