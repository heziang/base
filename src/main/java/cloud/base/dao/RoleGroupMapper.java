package cloud.base.dao;


import java.util.List;

import cloud.base.model.RoleGroup;


public interface RoleGroupMapper {
	List getListByPid(String pid);
	void save(RoleGroup roleGroup);
	void update(RoleGroup roleGroup);
	void delete(String[] ids);
	RoleGroup getRoleGroupById(String id);
	int groupIsHaschildren(String groupid);
}