package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.File;

public interface FileService {
	
	public void add(File file);
	
	public void delete(long id);
	public void delete(File file);
	
	public List<File> select(long id, long owner);
	
	public void update(File file);
}
