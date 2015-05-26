package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.File;

@Repository
@Transactional
public class FileDAOImpl implements FileDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private File file;
	
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
				.createQuery("delete from File f where f.id = :id");
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
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(File.class)
		.setProjection( Projections.distinct( Projections.projectionList()
			.add( Projections.property("id"), "id")
			.add( Projections.property("name"), "name")
			.add( Projections.property("owner"), "owner")
			.add( Projections.property("parent"), "parent")
			.add( Projections.property("path"), "path")
			.add( Projections.property("size"), "size")
			.add( Projections.property("type"), "type") ))
		.add( Restrictions.eq("parent", parent) )
		.setResultTransformer(Transformers.aliasToBean(File.class));
		return (List<File>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<File> getByOwner(long owner) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(File.class)
		.setProjection( Projections.distinct( Projections.projectionList()
			.add( Projections.property("id"), "id")
			.add( Projections.property("name"), "name")
			.add( Projections.property("owner"), "owner")
			.add( Projections.property("parent"), "parent")
			.add( Projections.property("path"), "path")
			.add( Projections.property("size"), "size")
			.add( Projections.property("type"), "type") ))
		.add( Restrictions.eq("owner", owner) )
		.setResultTransformer(Transformers.aliasToBean(File.class));
		return (List<File>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<File> getByParentAndOwner(long parent, long owner) {		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(File.class)
		.setProjection( Projections.distinct( Projections.projectionList()
			.add( Projections.property("id"), "id")
			.add( Projections.property("name"), "name")
			.add( Projections.property("owner"), "owner")
			.add( Projections.property("parent"), "parent")
			.add( Projections.property("path"), "path")
			.add( Projections.property("size"), "size")
			.add( Projections.property("type"), "type") ))
		.add( Restrictions.eq("parent", parent) )
		.add( Restrictions.eq("owner", owner) )
		.setResultTransformer(Transformers.aliasToBean(File.class));
		return (List<File>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public File getThumbnail(long id) {		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(File.class)
		.setProjection( Projections.distinct( Projections.projectionList()
			.add( Projections.property("thumbnail"), "thumbnail") ))
		.add( Restrictions.eq("id", id) )
		.setResultTransformer(Transformers.aliasToBean(File.class));
		return ((List<File>) criteria.list()).get(0);
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
				.createQuery("from File f where f.id = :id");
		query.setParameter("id", id);
		return (File) query.list().get(0);
	}


}
