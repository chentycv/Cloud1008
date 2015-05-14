package tk.Cloud1008.service;
import java.io.UnsupportedEncodingException;
import java.util.List;

import tk.Cloud1008.entity.User;
import tk.Cloud1008.exceptions.InvalidCookiesException;

public interface UsersService {
	public void save(User user);
	public List<User> getAll();
	public User get(long id);
	public void delete(User user);
	public void update(User user);
	public User getByCooikes(String cookies) throws UnsupportedEncodingException, InvalidCookiesException;
	public String getCurrentCookiesValue() throws UnsupportedEncodingException;
	public User getByLoginNameAndPassword(String loginName, String password);
	public User getByLoginName(String loginName);
}