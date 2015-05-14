package tk.Cloud1008.dao;

import tk.Cloud1008.entity.PersistentLogin;

public interface PersistentLoginDAO {

	void save(PersistentLogin persistentLogin);

	void delete(PersistentLogin persistentLogin);

	PersistentLogin getBySeriesAndToken(String series, String token);
}
