package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.File;

public interface FileService {
	
	public void add(File file);
	
	public void delete(long id);
	public void delete(File file);
	
	public void update(File file);

	public List<File> getByParent(long parent);

	public List<File> getByOwner(long owner);

	public List<File> getByParentAndOwner(long parent, long owner);
	
	public File getThumbnail(long id);
}
