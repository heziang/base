package cloud.base.model.VO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageModel {
	private List data;//记录
	private Integer rows;//一页显示的记录数
	private Integer page;//当前页数
	private Map conditions = new HashMap();//查询条件
	private PageData pageData = new PageData();//查询结果的封装
	
	public Map initPageModel(){
		conditions.put("start", (page-1)*rows);
		conditions.put("rows", rows);
		return conditions;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
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

	public PageData getPageData() {
		return pageData;
	}

	public void setPageData(PageData pageData) {
		this.pageData = pageData;
	}
}