package cloud.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import cloud.base.dao.RoleGroupMapper;
import cloud.base.model.RoleGroup;
import cloud.base.service.IRoleGroupService;

@Transactional
@Service
public class RoleGroupServiceImpl implements IRoleGroupService {

	@Autowired
	private RoleGroupMapper usergroupmapper;
	
	public List getListByPid(String pid) {
		return usergroupmapper.getListByPid(pid);
	}

	public void save(RoleGroup roleGroup) {
		usergroupmapper.save(roleGroup);
	}

	public void update(RoleGroup roleGroup) {
		usergroupmapper.update(roleGroup);
		
	}

	public void delete(String[] ids) {
		usergroupmapper.delete(ids);
	}

	public RoleGroup getRoleGroupById(String id) {
		return usergroupmapper.getRoleGroupById(id);
	}

	public int groupIsHaschildren(String groupid) {
		return usergroupmapper.groupIsHaschildren(groupid);
	}
}
