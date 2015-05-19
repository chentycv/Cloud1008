package tk.Cloud1008.service;
import java.util.List;

import tk.Cloud1008.entity.Friend;
import tk.Cloud1008.entity.User;

public interface FriendsService {
	public Friend save(Friend friend);
	public List<Friend> getAll();
	public Friend get(long id);
	public Friend delete(Friend friend);
	public Friend update(Friend friend);
	public List<Friend> getAllByUser(User user);
}