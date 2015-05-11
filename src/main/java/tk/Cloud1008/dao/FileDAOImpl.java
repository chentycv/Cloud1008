package tk.Cloud1008.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.File;

@Repository
public class FileDAOImpl implements FileDAO{

	@Autowired
	//Session factory injected by spring context
    SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public int addFile(File file) {
		this.sessionFactory.getCurrentSession().save(file);
		return 0;
	}

}
