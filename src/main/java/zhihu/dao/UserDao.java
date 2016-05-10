package zhihu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zhihu.model.User;

/**
 * Created by ZJ on 2016/4/13.
 */
@Repository
@Transactional
public interface UserDao extends JpaRepository<User,Long> {

	User save(User user);

	User findByUsername(String username);

	User findByUserID(long userID);

//	@Autowired
//	private JdbcOperations jdbcOperations;
//
//
//
//	private static final String INSERT_USER = "insert into user (username, password) values (?, ?)";
//	private static final String QUERY_USER_BY_USERNAME = "select * from user where username=?";
//	private static final String QUERY_USER_BY_USER_ID = "select * from user where user_id=?";
//
//	@CachePut(value = "userCache",key = "#result.userID")
//	public User registerNewUser(User user){
//		jdbcOperations.update(INSERT_USER,
//				user.getUsername(),
//				new MD5Util().encode(user.getPassword()));
//		return findUserByUserName(user.getUsername());
//	}
//
//
//	public User findUserByUserName(String username){
//		try {
//			User queryUser = jdbcOperations.queryForObject(
//					QUERY_USER_BY_USERNAME,
//					new UserRowMapper(),
//					username);
//			return queryUser;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//
//	}
//
//	@Cacheable(value = "userCache")
//	public User findUserByUserID(long user_id){
//		try {
//			User queryUser = jdbcOperations.queryForObject(
//					QUERY_USER_BY_USER_ID,
//					new UserRowMapper(),
//					user_id);
//			return queryUser;
//		}
//		catch (EmptyResultDataAccessException e){
//			return null;
//		}
//
//	}
//
//	private class UserRowMapper implements RowMapper<User> {
//		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//			return new User(
//					rs.getLong("user_id"),
//					rs.getString("username"),
//					rs.getString("password"));
//		}
//	}
}
