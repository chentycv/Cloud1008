package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.Friend;

@Repository
public class FriendsDAOImpl implements FriendsDAO {
	
	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	public void save(Friend user) {
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public void update(Friend user) {
		this.sessionFactory.getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Friend");
		List <Friend> friends = query.list();
		return friends;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Friend get(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Friend WHERE ID = :id");
		query.setParameter("id", id);
		List <Friend> friends = query.list();
		return friends.get(0);
	}

	@Override
	public void delete(Friend friend) {
    	this.sessionFactory.getCurrentSession().delete(friend);
	}

	@Override
	public List<Friend> getByUserAId(Long userAId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Friend WHERE userAId = :userAId");
		query.setParameter("userAId", userAId);
		List <Friend> friends = query.list();
		return friends;
	}
	
	@Override
	public List<Friend> getByUserBId(Long userBId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Friend WHERE userBId = :userBId");
		query.setParameter("userBId", userBId);
		List <Friend> friends = query.list();
		return friends;
	}
	
	
	@Override
	public Friend getByUserAIdAndUserBId(Long userAId, Long userBId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Friend WHERE userAId = :userAId AND userBId = :userBId");
		query.setParameter("userAId", userAId);
		query.setParameter("userBId", userBId);
		List <Friend> friends = query.list();
		if (friends.size() == 0){
			return null;
		} else {
			return friends.get(0);
		}
	}
}