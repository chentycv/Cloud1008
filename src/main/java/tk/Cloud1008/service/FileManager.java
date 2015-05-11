package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.File;

public interface FileManager {
	
	//This method will be called when a Files object is added
	public List<Object> addFile(File file);
}
