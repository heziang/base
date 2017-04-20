package cloud.base.util;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import cloud.base.model.SessionUser;
import cloud.base.model.SysResource;

public class SecurityUtil {
	/**
	 * @return //得到当前用户
	 */
	public SessionUser getSessionUser(){
		return (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
	}
	
	/**
	 * @return 所有用户资源
	 */
	public List<SysResource> getSessionUrl(){
		
		SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		//获取用户拥有的权限角色代码
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			grantedAuthority.getAuthority();
		}
		return null;
	}
	
}
