package tk.Cloud1008.dao;

import java.util.List;
import tk.Cloud1008.entity.Group;

public interface GroupDAO {

	public void add(Group group);
	
	public List<Group> getAll();
	public Group get(long id);

	public void delete(long id);
	public void delete(Group group);
	
	public void update(Group group);

}
