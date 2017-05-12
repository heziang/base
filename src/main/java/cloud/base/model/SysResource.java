package cloud.base.model;

/**
 * @author Heziang
 *	系统资源类
 *
 */
public class SysResource {
	private String resourcecode;
	private String groupcode;
	private String resourcename;
	private String resourcevalue;
	private String resourcetype;
	private String resourcetypename;//资源类型名称-非数据库字段
	private String groupname;//分组名称-非数据库字段
	private String resourceorder;
	public String getResourcecode() {
		return resourcecode;
	}
	public void setResourcecode(String resourcecode) {
		this.resourcecode = resourcecode;
	}
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getResourcevalue() {
		return resourcevalue;
	}
	public void setResourcevalue(String resourcevalue) {
		this.resourcevalue = resourcevalue;
	}
	public String getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}
	public String getResourceorder() {
		return resourceorder;
	}
	public void setResourceorder(String resourceorder) {
		this.resourceorder = resourceorder;
	}
	public String getResourcetypename() {
		return resourcetypename;
	}
	public void setResourcetypename(String resourcetypename) {
		this.resourcetypename = resourcetypename;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
