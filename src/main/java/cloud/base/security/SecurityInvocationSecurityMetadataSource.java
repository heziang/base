package cloud.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cloud.base.service.ISysUserService;
import cloud.base.service.UrlMatcher;
import cloud.base.service.impl.AntUrlPathMatcher;

public class SecurityInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	// 将所有的角色和url的对应关系缓存起来  Map<权限编码,权限所需的角色编码>  heza
	private static List<Map<String,String>> allResource = null;

	@Resource
	private ISysUserService sysuserservice;

	// 参数是要访问的url，返回这个url对于的所有权限（或角色）
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();

		// 查询所有的url和角色的对应关系
		if (allResource == null) {
			allResource = sysuserservice.findAllResources();
		}
		
		// 定义所需角色集合，利用set的特性，去重
		Set<String> roles = new HashSet<String>();
		//定义待返回的角色集合
		Collection<ConfigAttribute> cas = new ArrayList<ConfigAttribute>();
		
		//匹配权限所需角色，去重
		for(Map<String,String> allResourceMap : allResource){
			if(allResourceMap!=null){
				//如果allResourceMap.get("resourcevalue")为null，则证明有角色没有被赋予权限，忽略掉此角色
				if(allResourceMap.get("resourcevalue")!=null){
					if (urlMatcher.pathMatchesUrl(allResourceMap.get("resourcevalue"),url)) {
						roles.add(allResourceMap.get("rolecode"));
					}
				}
			}
		}
		
		//封装角色所需角色集合
		for (String role : roles) {
			ConfigAttribute ca = new SecurityConfig(role);
			cas.add(ca);
		}
		return cas;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}
