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
import tk.Cloud1008.dao.FriendsDAO;
import tk.Cloud1008.dao.UsersDAO;
import tk.Cloud1008.entity.PersistentLogin;
import tk.Cloud1008.entity.Friend;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.util.Base64;

@Service
public class FriendsServiceImpl implements FriendsService {

	@Autowired
	FriendsDAO friendsDAO;
	
	Friend friend;

	@Override
	@Transactional
	public Friend save(Friend friend) {
		Long setUserAId = friend.getUserAId();
		Long setUserBId = friend.getUserBId();
		
//		if (setUserAId > setUserBId) {
//			setUserAId = setUserAId ^ setUserBId;
//			setUserBId = setUserAId ^ setUserBId;
//			setUserAId = setUserBId ^ setUserAId;
//		}
		
		if ( setUserAId != setUserBId 
			 && friendsDAO.getByUserAIdAndUserBId(setUserAId, setUserBId) == null){
			friendsDAO.save(friend);
			return friend;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public List<Friend> getAll() {
		return friendsDAO.getAll();
	}

	@Override
	@Transactional
	public Friend get(long id) {
		return friendsDAO.get(id);
	}

	@Override
	@Transactional
	public Friend delete(Friend friend) {
		friendsDAO.delete(friend);
		return friend;
	}

	@Override
	@Transactional
	public Friend update(Friend friend) {
		friendsDAO.update(friend);
		return friend;
	}

	@Override
	@Transactional
	public List<Friend> getAllByUser(User user) {
		List<Friend> listA = friendsDAO.getByUserAId(user.getId());
		List<Friend> listB = friendsDAO.getByUserBId(user.getId());
		listA.addAll(listB);
		return listA;
	}
}
