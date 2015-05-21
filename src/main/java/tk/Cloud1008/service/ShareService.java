package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.File;
import tk.Cloud1008.entity.Share;

public interface ShareService {
	
	public void add(Share share);
	
	public void delete(long id);
	public void delete(Share share);
	
	public List<Share> select();
	public List<Share> getByFromUser(long fromuserid);
	public List<Share> getByToUser(long touserid);
	public List<Share> getByToGroup(long togroupid);
	public List<Share> getByType(long fromuserid);
	public List<Share> getByFromUserToUser(long fromuserid,long touserid);
	
	public void update(Share share);
}
