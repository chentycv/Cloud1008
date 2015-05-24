package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.User;

@Repository
public class UsersDAOImpl implements UsersDAO {
	
	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	public void save(User user) {
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public void update(User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}	

	@Override
	public void merge(User user) {
		this.sessionFactory.getCurrentSession().merge(user);
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User");
		List <User> users = query.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User get(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE ID = :id");
		query.setParameter("id", id);
		List <User> users = query.list();
		return users.get(0);
	}

	@Override
	public void delete(User user) {
    	this.sessionFactory.getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getByLoginNameAndPassword(String loginName, String password) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE loginName = :loginName AND password = :password");
		query.setParameter("loginName", loginName);
		query.setParameter("password", password);
		List <User> users = query.list();
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User getByLoginName(String loginName) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE loginName = :loginName");
		query.setParameter("loginName", loginName);
		List <User> users = query.list();
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public List<User> getAllBySearchTerm(String searchTerm) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE loginName like :searchTerm OR nickname like :searchTerm");
		query.setParameter("searchTerm", '%' + searchTerm + '%');
		return  query.list();
	}
}