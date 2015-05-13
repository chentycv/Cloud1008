package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.FileEntity;

public interface FileService {
	
	public void add(FileEntity file);
	
	public void delete(long id);
	public void delete(FileEntity file);
	
	public List<FileEntity> select(long id, long owner);
	
	public void update(FileEntity file);
}
