package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.File;

public interface FileHdfsDAO {
	
	public boolean sendFile(String path,String localfile);
	public boolean downloadFile(String hadfile,String localPath);

}
