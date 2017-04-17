package cloud.base.service;

import java.util.List;
import java.util.Map;

import cloud.base.model.SysResource;
import cloud.base.model.VO.PageModel;

public interface ISysResourceService {
	/**
	 * 得到资源
	 * @return
	 */
	SysResource loadResourceById(String id);
	
	/**
	 * 得到资源列表
	 * @return
	 */
	List<SysResource> search(Map conditions);
	/**
	 * 得到记录总数
	 * @return
	 */
	String getTotals(Map conditions);
	/**
	 * 封装分页数据
	 * @return
	 */
	PageModel loadPageModel(PageModel pageModel);
	/**
	 * @param 保存方法
	 */
	String saveSysResource(SysResource resource);
	
	/**
	 * @param 更新方法
	 */
	String updateSysResource(SysResource resource);
	/**
	 * @param userid
	 * 删除方法
	 * 
	 */
	String deleteSysResource(String[] ids);
	
}
