package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.FileEntity;

@Repository
@Transactional
public class FileDAOImpl implements FileDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addFileEntity(FileEntity file){

		this.sessionFactory.getCurrentSession().save(file);

	}

	@Override
	public void deleteFileEntityByID(long id) {
		// TODO Auto-generated method stub
				
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("delete from FileEntity f where f.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public void deleteFileEntity(FileEntity file) {
		// TODO Auto-generated method stub
		deleteFileEntityByID(file.getId());
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<FileEntity> selectFileEntityByParentID(long parentid, long owner) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from FileEntity f where f.parent = :id and f.owner = :owner");
		query.setParameter("id", parentid);
		query.setParameter("owner", owner);
		
		return (List<FileEntity>) query.list();
	}

	@Override
	public void updateFile(FileEntity file) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(file);
	}

	@Override
	public FileEntity selectFile(long id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from FileEntity f where f.id = :id");
		query.setParameter("id", id);
		return (FileEntity) query.list().get(0);
	}


}
