package cloud.base.service;

import java.util.List;

import cloud.base.model.UserGroup;

public interface IUserGroupService {
	List getListByPid(String pid);
	void save(UserGroup ug);
	void update(UserGroup ug);
	void delete(String[] ids);
	UserGroup getUserGroupById(String id);
}
