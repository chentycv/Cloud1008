package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.File;
import tk.Cloud1008.entity.Share;

public interface ShareService {
	
	public void add(Share share);
	
	public void delete(long id);
	public void delete(Share share);
	
	public List<Share> select();
	
	public void update(Share share);
}
