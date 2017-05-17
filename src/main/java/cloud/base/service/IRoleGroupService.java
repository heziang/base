package cloud.base.service;

import java.util.List;

import cloud.base.model.RoleGroup;

public interface IRoleGroupService {
	List getListByPid(String pid);
	void save(RoleGroup roleGroup);
	void update(RoleGroup roleGroup);
	void delete(String[] ids);
	RoleGroup getRoleGroupById(String id);
	int groupIsHaschildren(String groupid);
	int getRoleCountByGroupid(String groupid);
}
