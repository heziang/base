package cloud.base.util;


import org.springframework.security.core.context.SecurityContextHolder;

import cloud.base.model.SessionUser;

public class SecurityUtil {
	/**
	 * @return 得到当前用户
	 */
	public static SessionUser getSessionUser(){
		return (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
	}
}
