package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void save(User user) {
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	@Transactional
	public void update(User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User");
		List <User> users = query.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public User get(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE ID = :id");
		query.setParameter("id", id);
		List <User> users = query.list();
		return users.get(0);
	}

	@Override
	@Transactional
	public void delete(User user) {
    	this.sessionFactory.getCurrentSession().delete(user);
	}
}