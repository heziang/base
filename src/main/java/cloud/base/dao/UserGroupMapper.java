package cloud.base.dao;


import java.util.List;

import cloud.base.model.SysUser;
import cloud.base.model.UserGroup;


public interface UserGroupMapper {
	List getListByPid(String pid);
	void save(UserGroup ug);
	void update(UserGroup ug);
	void delete(String[] ids);
	UserGroup getUserGroupById(String id);
	int groupIsHaschildren(String groupid);
	int getUserCountByGroupId(String groupid);
}