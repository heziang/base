package cloud.base.model.VO;

import java.util.List;

public class PageData {
	private String total;//总记录数
	private List rows;//记录
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
