package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.FileEntity;
import tk.Cloud1008.entity.ShareEntity;

public interface ShareService {
	
	public void add(ShareEntity share);
	
	public void delete(long id);
	public void delete(ShareEntity share);
	
	public List<ShareEntity> select();
	
	public void update(ShareEntity share);
}
