package tk.Cloud1008.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.exceptions.InvalidDataAccessException;
import tk.Cloud1008.dao.PersistentLoginDAO;
import tk.Cloud1008.dao.UsersDAO;
import tk.Cloud1008.entity.PersistentLogin;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.util.Base64;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDAO usersDAO;
	
	@Autowired
	PersistentLoginDAO persistentLoginDAO;
	
	private SecureRandom random = new SecureRandom();
    public static final int DEFAULT_SERIES_LENGTH = 16;
    public static final int DEFAULT_TOKEN_LENGTH = 16;

    private int seriesLength = DEFAULT_SERIES_LENGTH;
    private int tokenLength = DEFAULT_TOKEN_LENGTH;

    private PersistentLogin persistentLogin;

	@Override
	@Transactional
	public void save(User user) {
		usersDAO.save(user);
	}

	@Override
	@Transactional
	public List<User> getAll() {
		return usersDAO.getAll();
	}

	@Override
	@Transactional
	public User get(long id) {
		return usersDAO.get(id);
	}

	@Override
	@Transactional
	public void delete(User user) {
		usersDAO.delete(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		usersDAO.update(user);
	}

	@Override
	@Transactional
	public User getByLoginName(String loginName) {
		return usersDAO.getByLoginName(loginName);
	}

	@Override
	@Transactional
	public User getByLoginNameAndPassword(String loginName, String password) {
		User user = usersDAO.getByLoginNameAndPassword(loginName, password);
		if ( user != null ) {
			persistentLogin = new PersistentLogin();
			persistentLogin.setSeries(generateSeriesData());
			persistentLogin.setToken(generateTokenData());
			persistentLoginDAO.save(persistentLogin);
			return user;
		} else {
			return null;
		}
	}
	
	@Override
	@Transactional
	public User getByCooikes(String cookies) throws UnsupportedEncodingException, InvalidCookiesException {
		String[] split = Base64Decode(cookies).split(":");
		String series = split[0];
		String token = split[1];
		persistentLogin = persistentLoginDAO.getBySeriesAndToken(series, token);
		if (persistentLogin != null){
			token = generateTokenData();
			persistentLogin.setSeries(series);
			persistentLogin.setToken(token);
			return usersDAO.get(persistentLogin.getUserID());
		} else {
			return null;
		}	
	}
	
	@Override
	@Transactional
	public String getCurrentCookiesValue() throws UnsupportedEncodingException {
		return Base64Encode(persistentLogin.getSeries() + ":" + persistentLogin.getToken());
	}
	
	private String Base64Decode(String value) throws UnsupportedEncodingException{
		return new String( Base64.decode(value.getBytes("ASCII")), "ASCII" ) ;
	}
	
	private String Base64Encode(String value) throws UnsupportedEncodingException{
		return new String( Base64.encode(value.getBytes("ASCII")), "ASCII" ) ;
	}
	
	protected String generateSeriesData() {
		byte[] newSeries = new byte[seriesLength];
		random.nextBytes(newSeries);
		return new String(Base64.encode(newSeries));
	}

    protected String generateTokenData() {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }




}
