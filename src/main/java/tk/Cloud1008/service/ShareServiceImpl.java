package tk.Cloud1008.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.dao.ShareDAO;
import tk.Cloud1008.entity.ShareEntity;

@Service
public class ShareServiceImpl implements ShareService{

	@Autowired
	private ShareDAO shareDAO;

	@Transactional
	@Override
	public void add(ShareEntity share) {
		// TODO Auto-generated method stub
		shareDAO.addShareEntity(share);
	}

	@Transactional
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		shareDAO.deleteShare(id);
	}

	@Transactional
	@Override
	public void delete(ShareEntity share) {
		// TODO Auto-generated method stub
		shareDAO.deleteShare(share);
	}

	@Override
	public List<ShareEntity> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ShareEntity share) {
		// TODO Auto-generated method stub
		shareDAO.updateShare(share);
	}
	

}
