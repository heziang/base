package cloud.base.model.VO;

import java.util.List;
import java.util.Map;

public class PageModel<E> {
	private List<E> data;//记录
	private Integer total;//总记录数
	private Integer rows;//一页显示的记录数
	private Integer page;//当前页数
	private Map<String,Object> conditions;//查询条件
	
	public PageModel<E> initPageModel(){
		conditions.put("start", (page-1)*rows);
		conditions.put("rows", rows);
		return this;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}
}