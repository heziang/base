package cloud.base.dao;


import cloud.base.model.SysUser;


public interface SysUserMapper {
	SysUser loadUserById(String userid);
	void save(SysUser u);
	void update(SysUser u);
	void delete(String[] userid);
}