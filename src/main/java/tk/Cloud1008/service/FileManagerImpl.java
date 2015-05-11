package tk.Cloud1008.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.Cloud1008.dao.FileDAO;
import tk.Cloud1008.entity.File;

@Service
public class FileManagerImpl implements FileManager{

	@Autowired
	FileDAO fileDAO;
	
	@Override
	public List<Object> addFile(File file) {
		// TODO Auto-generated method stub
		fileDAO.addFile(file);
		return null;
	}
	
}
