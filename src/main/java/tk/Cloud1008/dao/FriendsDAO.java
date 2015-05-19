package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.Friend;

public interface FriendsDAO {

	public void save(Friend user);

	public List<Friend> getAll();

	public Friend get(long id);

	public void delete(Friend user);

	public void update(Friend user);

	public List<Friend> getByUserAId(Long userAId);

	public List<Friend> getByUserBId(Long userAId);

	public Friend getByUserAIdAndUserBId(Long userAId, Long userBId);
}
