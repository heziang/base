package cloud.base.service;

import java.util.List;
import java.util.Map;

import cloud.base.model.SysRole;
import cloud.base.model.VO.PageModel;

public interface ISysRoleService {
	/**
	 * 得到角色
	 * @return
	 */
	SysRole loadResourceById(String id);
	
	/**
	 * 得到角色列表
	 * @return
	 */
	List<SysRole> search(Map conditions);
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
	String saveSysRole(SysRole role);
	
	/**
	 * @param 更新方法
	 */
	String updateSysRole(SysRole role);
	/**
	 * @param userid
	 * 删除方法
	 * 
	 */
	String deleteSysRole(String[] ids);
	
}
