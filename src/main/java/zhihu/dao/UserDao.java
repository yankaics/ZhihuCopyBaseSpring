package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.User;
import zhihu.util.MD5Util;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZJ on 2016/4/13.
 */
@Repository
public class UserDao {

	@Autowired
	private JdbcOperations jdbcOperations;

	private static final String INSERT_USER = "insert into user (username, password) values (?, ?)";
	private static final String QUERY_USER_BY_USERNAME = "select user_id, username, password from user where username=?";



	public void registerNewUser(User user){
		jdbcOperations.update(INSERT_USER,
				user.getUsername(),
				MD5Util.MD5(user.getPassword()));
	}


	public String login(User user, HttpSession session){
		User queryUser = jdbcOperations.queryForObject(
											QUERY_USER_BY_USERNAME,
											new UserRowMapper(),
											user.getUsername());
		if (queryUser==null)
			return "不存在该账户";
		else if(queryUser.getPassword().equals(MD5Util.MD5(user.getPassword()))){
			session.setAttribute("user",user);
			return "success";
		}
		else {
			return "密码错误";
		}

	}

	private static class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(
					rs.getLong("user_id"),
					rs.getString("username"),
					rs.getString("password"));
		}
	}
}
