package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.File;

public interface FileDAO {
	
	public void addFileEntity(File file);
	
	public void deleteFileEntityByID(long id);
	
	public void deleteFileEntity(File file);

	public File selectFile(long id);
	
	public void updateFile(File file);

	public List<File> getByParent(long parent);

	public List<File> getByOwner(long owner);

	public List<File> getByParentAndOwner(long parent, long owner);

}
