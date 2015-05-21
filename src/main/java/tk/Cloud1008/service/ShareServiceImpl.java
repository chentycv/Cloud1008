package tk.Cloud1008.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.dao.ShareDAO;
import tk.Cloud1008.entity.Share;

@Service
public class ShareServiceImpl implements ShareService{

	@Autowired
	private ShareDAO shareDAO;

	@Transactional
	@Override
	public void add(Share share) {
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
	public void delete(Share share) {
		// TODO Auto-generated method stub
		shareDAO.deleteShare(share);
	}

	@Transactional
	@Override
	public List<Share> select() {
		// TODO Auto-generated method stub
		shareDAO.selectShare();
		return null;
	}

	@Transactional
	@Override
	public void update(Share share) {
		// TODO Auto-generated method stub
		shareDAO.updateShare(share);
	}

	@Transactional
	@Override
	public List<Share> getByFromUser(long fromuserid) {
		// TODO Auto-generated method stub
		return shareDAO.getByFromUser(fromuserid);
	}

	@Transactional
	@Override
	public List<Share> getByToUser(long touserid) {
		// TODO Auto-generated method stub
		return shareDAO.getByToUser(touserid);
	}

	@Transactional
	@Override
	public List<Share> getByToGroup(long togroupid) {
		// TODO Auto-generated method stub
		return shareDAO.getByToGroup(togroupid);
	}

	@Transactional
	@Override
	public List<Share> getByType(long fromuserid) {
		// TODO Auto-generated method stub
		return shareDAO.getByType(fromuserid);
	}

	@Transactional
	@Override
	public List<Share> getByFromUserToUser(long fromuserid, long touserids) {
		// TODO Auto-generated method stub
		return shareDAO.getByFromUserToUser(fromuserid, touserids);
	}
	

}
