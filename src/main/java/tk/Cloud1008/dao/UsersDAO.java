package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.User;

public interface UsersDAO {

	public void save(User user);

	public List<User> getAll();

	public User get(long id);

	public void delete(User user);

	public void update(User user);

	public User getByLoginNameAndPassword(String loginName, String password);

	public User getByLoginName(String loginName);

	public List<User> getAllBySearchTerm(String searchTerm);
}
