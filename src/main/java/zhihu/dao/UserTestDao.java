package zhihu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zhihu.model.User;

/**
 * Created by ZJ on 2016/4/26.
 */

@Repository
@Transactional
public interface UserTestDao extends JpaRepository<User,Long>{

	User save(User user);
}
