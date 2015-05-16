package tk.Cloud1008.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.dao.GroupDAO;
import tk.Cloud1008.dao.UserGroupDAO;
import tk.Cloud1008.entity.Group;
import tk.Cloud1008.entity.UserGroup;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO GroupDAO;
	
	@Autowired
	private UserGroupDAO usergroupDAO;

	@Override
	@Transactional
	public void save(Group Group) {
		GroupDAO.add(Group);
	}

	@Override
	@Transactional
	public List<Group> getAll() {
		return GroupDAO.getAll();
	}

	@Transactional
	@Override
	public Group get(long id) {
		return GroupDAO.get(id);
	}

	@Transactional
	@Override
	public void delete(Group Group) {
		GroupDAO.delete(Group);
	}

	@Transactional
	@Override
	public void update(Group Group) {
		GroupDAO.update(Group);
	}

	@Transactional
	@Override
	public List<UserGroup> getUserGroup(long groupid) {
		// TODO Auto-generated method stub
		return usergroupDAO.selectUsers(groupid);
	}
}
