package cloud.base.service;

import java.util.List;
import java.util.Map;

import cloud.base.model.SysLog;
import cloud.base.model.VO.PageModel;



public interface ISysLogService {
	List<SysLog> search(Map conditions);
	void save(SysLog sysLog);
	
	/**
	 * @param map
	 *   将属性值封装到map中保存
	 */
	void save(Map<String,Object> map);
	
	
	/**
	 * 分页查询
	 * @param pageModel
	 * @return
	 */
	PageModel loadPageModel(PageModel pageModel);
	
	/**
	 * 得到记录数
	 * @param pageModel
	 * @return
	 */
	String getTotals(Map conditions);
}
