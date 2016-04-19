package zhihu.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZJ on 2016/4/19.
 */
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
		request.getSession().setAttribute("user", customUserDetail);
		super.handle(request, response, authentication);
	}
}
