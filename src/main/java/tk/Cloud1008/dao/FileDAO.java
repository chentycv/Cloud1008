package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.File;

public interface FileDAO {
	
	public void addFileEntity(File file);
	
	public void deleteFileEntityByID(long id);
	
	public void deleteFileEntity(File file);
	
	public List<File> selectFileEntityByParentID(long id,long owner);
	public File selectFile(long id);
	
	public void updateFile(File file);

}
