package cloud.base.model;

/**
 * @author Heziang
 * 系统角色类
 */
public class SysRole extends BaseModel{
	private String rolecode;
	private String rolename;
	private String groupcode;
	private Integer roleorder;
	
	public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
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
