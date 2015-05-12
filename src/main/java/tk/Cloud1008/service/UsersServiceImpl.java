package tk.Cloud1008.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.Cloud1008.dao.UserDAO;
import tk.Cloud1008.entity.User;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UserDAO userDAO;

	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User get(long id) {
		return userDAO.get(id);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}
}
