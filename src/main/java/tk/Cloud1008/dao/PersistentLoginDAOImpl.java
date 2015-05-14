package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.entity.PersistentLogin;
import tk.Cloud1008.entity.User;

@Repository
public class PersistentLoginDAOImpl implements PersistentLoginDAO {
	
	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	public void save(PersistentLogin persistentLogin) {
		this.sessionFactory.getCurrentSession().save(persistentLogin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PersistentLogin getBySeriesAndToken(String series, String token) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM PersistentLogin WHERE series = :series AND token = :token");
		query.setParameter("series", series);
		query.setParameter("token", token);		
		List <PersistentLogin> persistentLogins = query.list();
		if (persistentLogins.size() == 0) {
			return null;
		} else {
			return persistentLogins.get(0);
		}
	}

	@Override
	public void delete(PersistentLogin persistentLogin) {
    	this.sessionFactory.getCurrentSession().delete(persistentLogin);
	}
}