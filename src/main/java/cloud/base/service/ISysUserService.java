package cloud.base.service;

import java.util.List;
import java.util.Map;

import cloud.base.model.SysUser;
import cloud.base.model.Userinfo;
import cloud.base.model.VO.PageModel;

public interface ISysUserService {
	/**
	 * 得到所有的角色 与 权限 的对应信息 Map<权限编码,权限所需的角色编码>
	 * @return
	 * 
	 */
	List<String> findAllResourcesByUserId(String userid);
	
	/**
	 * 得到用户的角色 与 权限 的对应信息 Map<权限编码,权限所需的角色编码>
	 * @return
	 * 
	 */
	List<Map<String,String>> findAllResources();
	
	/**
	 * 得到用户
	 * @return
	 */
	SysUser loadUserById(String username);
	
	/**
	 * 得到用户列表
	 * @return
	 */
	List<SysUser> search(Map conditions);
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
	 * @param u user对象  保存方法
	 */
	String saveSysUser(SysUser u,Userinfo userinfo);
	
	/**
	 * @param u 更新方法
	 */
	String updateUserinfo(SysUser u,Userinfo userinfo);
	/**
	 * @param userid
	 * 删除用户
	 * 
	 */
	String deleteSysUser(String[] userid);
	
	/**
	 * 得到用户信息
	 * @param id
	 * @return 
	 */
	Userinfo getUserinfoById(String id);
}
