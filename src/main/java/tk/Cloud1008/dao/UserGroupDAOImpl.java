package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.UserGroup;

@Repository
public class UserGroupDAOImpl implements UserGroupDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void add(UserGroup usersgroups) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(usersgroups);
	}

	@Override
	public void deleteByID(long id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("delete from UserGroup u where u.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@Override
	public void deleteByUserID(long id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("delete from UserGroup u where u.userid = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void deleteByGroupID(long id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("delete from UserGroup u where u.groupid = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void delete(long userid, long groupid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("delete from UserGroup u where u.userid = :userid and u.groupid = :groupid");
		query.setParameter("userid", userid);
		query.setParameter("groupid", groupid);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> selectUsers(long groupid) {
		// TODO Auto-generated method stub
		
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from UserGroup u where u.groupid= :groupid");
		
		query.setParameter("groupid", groupid);
		List<UserGroup> usersgroups = query.list();
		return usersgroups;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> selectGroups(long userid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from UserGroup u where u.userid = :userid");
		query.setParameter("userid", userid);
		List<UserGroup> usersgroups = query.list();
		return usersgroups;
	}

}
