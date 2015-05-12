package tk.Cloud1008.service;
import java.util.List;

import tk.Cloud1008.entity.User;

public interface UsersService {
	public void save(User user);
	public List<User> getAll();
	public User get(long id);
	public void delete(User user);
	public void update(User user);
}

