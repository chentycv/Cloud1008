package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.Group;
import tk.Cloud1008.entity.User;

@Repository
public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(Group group) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(group);
	}

	@Override
	public List<Group> getAll() {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Group");
		List <Group> groups = query.list();
		return groups;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"delete from Group g where g.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void delete(Group group) {
		// TODO Auto-generated method stub
		delete(group.getId());
	}

	@Override
	public void update(Group group) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(group);
	}

	@Override
	public Group get(long id) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM Group WHERE ID = :id");
		query.setParameter("id", id);
		List<Group> group = query.list();
		return group.get(0);
	}

}
