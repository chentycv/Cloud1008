package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.File;

@Repository
@Transactional
public class FileDAOImpl implements FileDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addFileEntity(File file){

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
	public void deleteFileEntity(File file) {
		// TODO Auto-generated method stub
		deleteFileEntityByID(file.getId());
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<File> getByParent(long parent) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from File f where f.parent = :parent");
		query.setParameter("parent", parent);
		
		return (List<File>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<File> getByOwner(long owner) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from File f where f.owner = :owner");
		query.setParameter("owner", owner);
		
		return (List<File>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<File> getByParentAndOwner(long parent, long owner) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from File f where f.parent = :id and f.owner = :owner");
		query.setParameter("id", parent);
		query.setParameter("owner", owner);
		
		return (List<File>) query.list();
	}
	@Override
	public void updateFile(File file) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(file);
	}

	@Override
	public File selectFile(long id) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery("from FileEntity f where f.id = :id");
		query.setParameter("id", id);
		return (File) query.list().get(0);
	}


}
