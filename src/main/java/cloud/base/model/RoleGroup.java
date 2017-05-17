package cloud.base.model;

/**
 * @author Heziang
 *
 * 角色分组类
 * 
 */
public class RoleGroup extends BaseModel{
	private String groupcode;
	private String groupname;
	private String grouptype;
	private String pgroupcode;
	private Integer gorder;
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getPgroupcode() {
		return pgroupcode;
	}
	public void setPgroupcode(String pgroupcode) {
		this.pgroupcode = pgroupcode;
	}
	public Integer getGorder() {
		return gorder;
	}
	public void setGorder(Integer gorder) {
		this.gorder = gorder;
	}
	
}
