package cloud.base.dao;


import java.util.List;
import java.util.Map;

import cloud.base.model.SysUser;
import cloud.base.model.Userinfo;


public interface UserinfoMapper {
	List<SysUser> search(Map conditions);
	String getTotal(Map conditions);
	void save(Userinfo userinfo);
	void delete(String[] userid);
	void update(Userinfo userinfo);
	Userinfo getUserinfoById(String id);
}