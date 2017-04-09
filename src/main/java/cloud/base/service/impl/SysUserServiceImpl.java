package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import cloud.base.dao.SysUserMapper;
import cloud.base.dao.UserRoleResourceMapper;
import cloud.base.dao.UserinfoMapper;
import cloud.base.model.SysUser;
import cloud.base.model.Userinfo;
import cloud.base.service.ISysUserService;

@Transactional
@Service
public class SysUserServiceImpl implements ISysUserService {
	@Autowired
	private UserRoleResourceMapper roleurlresourcemapper;
	
	@Autowired
	private SysUserMapper sysusermapper;
	
	@Autowired
	private UserinfoMapper userinfomapper;
	
	public List<String> findAllResourcesByUserId(String userId) {
		return roleurlresourcemapper.findAllResourcesByUserId(userId);
	}

	public List<Map<String, String>> findAllResources() {
		return roleurlresourcemapper.findAllResources();
	}

	public SysUser loadUserById(String userid) {
		return sysusermapper.loadUserById(userid);
	}

	public List<SysUser> search(Map conditions) {
		return userinfomapper.search(conditions);
	}

	public String getTotals(Map conditions) {
		return userinfomapper.getTotal(conditions);
	}

	public String saveSysUser(SysUser u,Userinfo userinfo) {
		sysusermapper.save(u);
		userinfomapper.save(userinfo);
		return u.getUserid();
	}
	public String updateUserinfo(SysUser u,Userinfo userinfo){
		userinfomapper.update(userinfo);
//		sysusermapper.update(u);
		return null;
	}
	public String deleteSysUser(String[] userid) {
		userinfomapper.delete(userid);
		sysusermapper.delete(userid);	
		return null;
	}

	public Userinfo getUserinfoById(String id) {
		return userinfomapper.getUserinfoById(id);
	}
}
