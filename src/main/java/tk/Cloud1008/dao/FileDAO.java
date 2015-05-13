package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.FileEntity;

public interface FileDAO {
	
	public void addFileEntity(FileEntity file);
	
	public void deleteFileEntityByID(long id);
	
	public void deleteFileEntity(FileEntity file);
	
	public List<FileEntity> selectFileEntityByParentID(long id,long owner);
	public FileEntity selectFile(long id);
	
	public void updateFile(FileEntity file);

}
