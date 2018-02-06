package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import cloud.base.annotation.SystemLog;
import cloud.base.dao.SysUserMapper;
import cloud.base.dao.UserRoleResourceMapper;
import cloud.base.dao.UserinfoMapper;
import cloud.base.model.SysResource;
import cloud.base.model.SysUser;
import cloud.base.model.UserRRole;
import cloud.base.model.Userinfo;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysUserService;
import cloud.base.util.SecurityPasswordEncoder;

@Transactional
@Service
public class SysUserServiceImpl implements ISysUserService {
	@Autowired
	private UserRoleResourceMapper userroleresourcemapper;
	
	@Autowired
	private SysUserMapper sysusermapper;
	
	@Autowired
	private UserinfoMapper userinfomapper;
	
	public List<String> findAllRolesByUserId(String userId) {
		return userroleresourcemapper.findAllRolesByUserId(userId);
	}

	public List<Map<String, String>> findAllResources() {
		return userroleresourcemapper.findAllResources();
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
	
	@SystemLog(description="新增用户",module="用户管理")
	@Transactional
	public String saveSysUser(SysUser u,Userinfo userinfo) {
		//密码加密
		u.setPwd(SecurityPasswordEncoder.encodeMd5HashAsBase64(u.getPwd(), null));
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
		//不作真删
//		userinfomapper.delete(userid);
		sysusermapper.delete(userid);	
		return null;
	}

	public Userinfo getUserinfoById(String id) {
		return userinfomapper.getUserinfoById(id);
	}

	public PageModel loadPageModel(PageModel pageModel) {
		pageModel.getPageData().setTotal(this.getTotals(pageModel.getConditions()));
		pageModel.getPageData().setRows(this.search(pageModel.getConditions()));
		return pageModel;
	}

	public String saveUserRole(String userid, String[] rolecodes) {
		UserRRole urr = null;
		//删除之前选择的角色
		if(!StringUtils.isEmpty(userid)){
			this.deleteRoleByUserId(userid);
		}
		//保存角色
		for (String rolecode : rolecodes) {
			if(!StringUtils.isEmpty(rolecode)){
				urr = new UserRRole();
				urr.setUserid(userid);
				urr.setRolecode(rolecode);
				userroleresourcemapper.saveUserRole(urr);
			}
		}
		return null;
	}

	public String deleteRoleByUserId(String userid) {
		userroleresourcemapper.deleteRoleByUserId(userid);
		return null;
	}
    @SystemLog(description="查询菜单",module="系统")
	public List getAllResourcesByUserId(String userid) {
		return userroleresourcemapper.getAllResourcesByUserId(userid);
	}
	
	public String changeUsersPassword(String userid,String password) {
		password = SecurityPasswordEncoder.encodeMd5HashAsBase64(password, null);
		SysUser user= new SysUser();
		user.setUserid(userid);
		user.setPwd(password);
		sysusermapper.update(user);
		return "";
	}
}
