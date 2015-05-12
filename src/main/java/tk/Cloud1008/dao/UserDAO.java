package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.User;

public interface UserDAO {

	void save(User user);

	List<User> getAll();

	User get(long id);

	void delete(User user);

	void update(User user);
}
