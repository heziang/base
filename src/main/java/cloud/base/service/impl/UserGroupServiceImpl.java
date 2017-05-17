package cloud.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;








import cloud.base.dao.UserGroupMapper;
import cloud.base.dao.UserinfoMapper;
import cloud.base.model.UserGroup;
import cloud.base.service.IUserGroupService;

@Transactional
@Service
public class UserGroupServiceImpl implements IUserGroupService {

	@Autowired
	private UserGroupMapper usergroupmapper;
	
	public List getListByPid(String pid) {
		return usergroupmapper.getListByPid(pid);
	}

	public void save(UserGroup ug) {
		usergroupmapper.save(ug);
	}

	public void update(UserGroup ug) {
		usergroupmapper.update(ug);
		
	}

	public void delete(String[] ids) {
		usergroupmapper.delete(ids);
	}

	public UserGroup getUserGroupById(String id) {
		return usergroupmapper.getUserGroupById(id);
	}

	public int groupIsHaschildren(String groupid) {
		return usergroupmapper.groupIsHaschildren(groupid);
	}

	public int getUserCountByGroupId(String groupid) {
		return usergroupmapper.getUserCountByGroupId(groupid);
	}
}
