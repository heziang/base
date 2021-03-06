package cloud.base.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author Heziang
 *  用户类的拓展
 */
@SuppressWarnings("serial")
public class SessionUser extends User{
	 private Userinfo userinfo;
	 private List<SysResource> resources;
	 
	 public SessionUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	        super(username,password,authorities);
	    }
	 
	public SessionUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public List<SysResource> getResources() {
		return resources;
	}

	public void setResources(List<SysResource> resources) {
		this.resources = resources;
	}
}
