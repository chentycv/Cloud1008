package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.Share;

public interface ShareDAO {
	
	public void addShareEntity(Share share);
	
	public void deleteShare(long id);
	
	public void deleteShare(Share share);
	
	public List<Share> selectShare();
	
	public void updateShare(Share share);

}
