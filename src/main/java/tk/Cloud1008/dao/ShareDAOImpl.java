package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.Cloud1008.entity.Share;

@Repository
public class ShareDAOImpl implements ShareDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void deleteShare(long id) {
		// TODO Auto-generated method stub			
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("delete from ShareEntity s where s.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@Override
	public void deleteShare(Share share) {
		// TODO Auto-generated method stub
		this.deleteShare(share.getId());
	}

	@Override
	public List<Share> selectShare() {
		// TODO Auto-generated method stub
		
/*		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from FileEntity f where f.parent = :id and f.owner = :owner");
		query.setParameter("id", parentid);
		query.setParameter("owner", owner);
		
		return (List<FileEntity>) query.list();*/
		return null;
	}

	@Override
	public void updateShare(Share share) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(share);
	}

	@Override
	public void addShareEntity(Share share) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(share);
	}

	@Override
	public List<Share> getByFromUser(long fromuserid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from Share s where s.fromUser = :id").setParameter("id", fromuserid);
		List<Share> shares = query.list();
		
		return shares;
	}

	@Override
	public List<Share> getByToUser(long touserid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from Share s where s.toUser = :id").setParameter("id", touserid);
		
		List<Share> shares = query.list();
		
		return shares;
	}

	@Override
	public List<Share> getByToGroup(long togroupid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from Share s where s.toGroup = :id").setParameter("id", togroupid);
		List<Share> shares = query.list();
		
		return shares;
	}

	@Override
	public List<Share> getByType(long fromuserid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from Share s where s.fromUser = :id and s.type = 'PUBLIC' ").setParameter("id", fromuserid);
		List<Share> shares = query.list();
		
		return shares;
	}

	@Override
	public List<Share> getByFromUserToUser(long fromuserid, long touserid) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from Share s where s.fromUser = :fromuserid and s.touserid=:touserid").setParameter("id", fromuserid)
				.setParameter("touserid", touserid);
		List<Share> shares = query.list();
		
		return shares;
	}


}
