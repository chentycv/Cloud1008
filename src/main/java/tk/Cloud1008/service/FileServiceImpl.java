package tk.Cloud1008.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.dao.FileDAO;
import tk.Cloud1008.entity.File;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileDAO fileDAO;
	
	@Transactional
	@Override
	public void add(File file)
	{
		fileDAO.addFileEntity(file);
	}
	
	@Transactional
	@Override
	public List<File> select(long parentid, long owner) {
		// TODO Auto-generated method stub
		return fileDAO.selectFileEntityByParentID(parentid, owner);
	}
	
	public FileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	@Transactional
	@Override
	public void update(File file) {
		// TODO Auto-generated method stub
		this.fileDAO.updateFile(file);
	}

	@Transactional
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		this.fileDAO.deleteFileEntityByID(id);
	}
	
	@Transactional
	@Override
	public void delete(File file) {
		// TODO Auto-generated method stub
		file = fileDAO.selectFile(file.getId());
		List<File> fileobj  = fileDAO.selectFileEntityByParentID(file.getId(), file.getOwner());
		this.fileDAO.deleteFileEntity(file);
		for(Object obj : fileobj)
		{
			File o = (File) obj;
			System.out.println(o.getId());
			delete(o);
		}
		
	}
	
}
