package zhihu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhihu.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ZJ on 2016/4/26.
 */

@Service
@Transactional
public class UserTestService {

	@PersistenceContext
	private EntityManager entityManager;

	public void addUser(User user){
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		//entityManager.getTransaction().begin();

		entityManager.persist(user);
		//entityManager.flush();
		//entityManager.getTransaction().commit();
	}
}
