package tk.Cloud1008.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.dao.FileHdfsDAO;


@Service
public class HdfsServiceImpl implements HdfsService{

	@Autowired
	private FileHdfsDAO filehdfsdao;

	@Transactional
	@Override
	public void upload(String hdfspath, String localpath) {
		// TODO Auto-generated method stub
		filehdfsdao.sendFile(hdfspath, localpath);
	}

	@Transactional
	@Override
	public void download(String hdfspath, String localpath) {
		// TODO Auto-generated method stub
		filehdfsdao.downloadFile(hdfspath, localpath);
	}
	
	
	
}
