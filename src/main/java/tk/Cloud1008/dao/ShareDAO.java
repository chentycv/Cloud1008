package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.Share;

public interface ShareDAO {
	
	public void addShareEntity(Share share);
	
	public void deleteShare(long id);
	
	public void deleteShare(Share share);
	
	public List<Share> selectShare();
	
	public List<Share> getByFromUser(long fromuserid);
	public List<Share> getByToUser(long touserid);
	public List<Share> getByToGroup(long togroupid);
	public List<Share> getByType(long fromuserid);
	public List<Share> getByFromUserToUser(long fromuserid,long touserids);
	
	public void updateShare(Share share);

}
