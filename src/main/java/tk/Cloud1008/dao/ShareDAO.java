package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.ShareEntity;

public interface ShareDAO {
	
	public void addShareEntity(ShareEntity share);
	
	public void deleteShare(long id);
	
	public void deleteShare(ShareEntity share);
	
	public List<ShareEntity> selectShare();
	
	public void updateShare(ShareEntity share);

}
