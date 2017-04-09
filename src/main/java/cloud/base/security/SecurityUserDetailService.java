package cloud.base.security;


import java.util.ArrayList;
import java.util.Collection;
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

import cloud.base.model.SessionUser;
import cloud.base.model.SysUser;
import cloud.base.service.ISysUserService;

public class SecurityUserDetailService implements UserDetailsService { 
	
	@Resource
	private ISysUserService sysuserservice;
	
	//登陆验证时，通过username获取用户的所有权限信息，
	//并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException, DataAccessException {   
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>(); 
		
		SysUser u = sysuserservice.loadUserById(username);
		
		for (String code : sysuserservice.findAllResourcesByUserId(username)) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(code);
			auths.add(authority);
		}
		
		SessionUser user = new SessionUser(username, u.getPwd(), true, true, true, true, auths); 
		return user;  
		} 
	} 
