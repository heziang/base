package cloud.base.model.VO;

import java.util.List;

public class EasyUITreeVO {
	private String id;
	private String text;
	private String iconCls;
	private String checked;
	private String state;
	private String attribute;
	private String target;
	List<EasyUITreeVO> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<EasyUITreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITreeVO> children) {
		this.children = children;
	}
	
}
