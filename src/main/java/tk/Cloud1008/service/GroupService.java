package tk.Cloud1008.service;
import java.util.List;

import tk.Cloud1008.entity.Group;
import tk.Cloud1008.entity.UserGroup;

public interface GroupService {
	
	public void save(Group group);
	public List<Group> getAll();
	public Group get(long id);	
	public void delete(Group Group);
	
	public void update(Group Group);
	
	public List<UserGroup> getUserGroup(long groupid);
}

