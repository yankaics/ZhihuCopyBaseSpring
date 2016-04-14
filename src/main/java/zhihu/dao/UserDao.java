package zhihu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zhihu.domain.User;
import zhihu.util.MD5Util;

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
	private static final String QUERY_USER_BY_USERNAME = "select * from user where username=?";



	public User registerNewUser(User user){
		jdbcOperations.update(INSERT_USER,
				user.getUsername(),
				MD5Util.MD5(user.getPassword()));
		return findUser(user);
	}

	public User findUser(User user){
		try {
			User queryUser = jdbcOperations.queryForObject(
					QUERY_USER_BY_USERNAME,
					new UserRowMapper(),
					user.getUsername());
			return queryUser;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}

	}


	private class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new User(
					rs.getLong("user_id"),
					rs.getString("username"),
					rs.getString("password"));
		}
	}
}
