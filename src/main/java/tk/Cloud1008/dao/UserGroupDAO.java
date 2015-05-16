package tk.Cloud1008.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.Group;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.entity.UserGroup;

public interface UserGroupDAO {
	
	public void add(UserGroup usersgroups);
	
	public void deleteByID(long id);
	
	public void deleteByUserID(long id);
	
	public void deleteByGroupID(long id);
	
	public void delete(long userid,long groupid);
	
	
	public List<UserGroup> selectUsers(long groupid);
	
	public List<UserGroup> selectGroups(long userid);
	

}
