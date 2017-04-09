package cloud.base.model;

/**
 * @author Heziang
 * 系统角色类
 */
public class SysRole {
	private String roleId;
	private String roleName;
	private String groupcode;
	private Integer roleorder;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public Integer getRoleorder() {
		return roleorder;
	}
	public void setRoleorder(Integer roleorder) {
		this.roleorder = roleorder;
	}
	
}
