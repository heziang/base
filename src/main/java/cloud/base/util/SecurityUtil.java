package cloud.base.util;


import org.springframework.security.core.context.SecurityContextHolder;

import cloud.base.model.SessionUser;

public class SecurityUtil {
	/**
	 * @return 得到当前用户
	 */
	public static SessionUser getSessionUser(){
		//如果未登录，返回null，如果登陆，返回sessionuser
		if("anonymousUser".equals( SecurityContextHolder.getContext().getAuthentication() .getPrincipal())){
			return null;
		}else{
			return (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		}
	}
}
