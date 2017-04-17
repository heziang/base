package cloud.base.dao;

import java.util.List;
import java.util.Map;

import cloud.base.model.SysRole;

public interface SysRoleMapper {
	SysRole loadUserById(String id);
	void save(SysRole sysRole);
	void update(SysRole sysRole);
	void delete(String[] ids);
	List<SysRole> search(Map conditions);
	String getTotal(Map conditions);
}