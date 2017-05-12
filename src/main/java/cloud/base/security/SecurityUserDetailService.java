package cloud.base.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import cloud.base.model.SessionUser;
import cloud.base.model.SysResource;
import cloud.base.model.SysUser;
import cloud.base.service.ISysResourceService;
import cloud.base.service.ISysUserService;

public class SecurityUserDetailService implements UserDetailsService { 
	
	@Resource
	private ISysUserService sysuserservice;
	@Resource
	private ISysResourceService sysresourceservice;
	
	//登陆验证时，通过username获取用户的所有权限信息，
	//并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException, DataAccessException {   
		
		List<SysResource> rslist = new ArrayList<SysResource>();
		//封装sessionuser信息。
		//得到sessionuser的所有角色
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>(); 
		SysUser u = sysuserservice.loadUserById(username);
		for (String code : sysuserservice.findAllRolesByUserId(username)) {
			if(!StringUtils.isEmpty(code)){
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(code);
				List<SysResource> clist = sysresourceservice.findAllResourceByRoleCode(authority.getAuthority());
				rslist.addAll(clist);
				auths.add(authority);
			}
		}
		
		
		SessionUser user = new SessionUser(username, u.getPwd(), true, true, true, true, auths); 
		
		//得到用户的详细信息
		user.setUserinfo(sysuserservice.getUserinfoById(username));
		//得到用户角色对应的所有权限
		user.setResources(rslist);
		return user;  
		} 
	} 
