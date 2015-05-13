package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.FileEntity;
import tk.Cloud1008.entity.ShareEntity;

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
	public void deleteShare(ShareEntity share) {
		// TODO Auto-generated method stub
		this.deleteShare(share.getId());
	}

	@Override
	public List<ShareEntity> selectShare() {
		// TODO Auto-generated method stub
		
/*		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from FileEntity f where f.parent = :id and f.owner = :owner");
		query.setParameter("id", parentid);
		query.setParameter("owner", owner);
		
		return (List<FileEntity>) query.list();*/
		return null;
	}

	@Override
	public void updateShare(ShareEntity share) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(share);
	}

	@Override
	public void addShareEntity(ShareEntity share) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(share);
	}


}
