package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;











import org.springframework.util.StringUtils;

import cloud.base.dao.SysRoleMapper;
import cloud.base.dao.UserRoleResourceMapper;
import cloud.base.model.RoleRResource;
import cloud.base.model.SysRole;
import cloud.base.model.UserRRole;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysRoleService;

@Transactional
@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper sysrolemapper;
	
	@Autowired
	private UserRoleResourceMapper userroleresourcemapper;
	

	public SysRole loadResourceById(String id) {
		return sysrolemapper.getSysRoleById(id);
	}

	public List<SysRole> search(Map conditions) {
		return sysrolemapper.search(conditions);
	}

	public String getTotals(Map conditions) {
		return sysrolemapper.getTotal(conditions);
	}

	public PageModel loadPageModel(PageModel pageModel) {
		pageModel.getPageData().setTotal(this.getTotals(pageModel.getConditions()));
		pageModel.getPageData().setRows(this.search(pageModel.getConditions()));
		return pageModel;
	}

	public String saveSysRole(SysRole role) {
		sysrolemapper.save(role);
		return null;
	}

	public String updateSysRole(SysRole role) {
		sysrolemapper.update(role);
		return null;
	}

	public String deleteSysRole(String[] ids) {
		sysrolemapper.delete(ids);
		return null;
	}

	public List<SysRole> findAllRoleByUserId(String userid) {
		return userroleresourcemapper.findAllRoleByUserId(userid);
	}

	public String saveRoleResources(String rolecode, String[] resourcecodes) {
		RoleRResource rrr = null;
		//删除之前选择的角色
		if(!StringUtils.isEmpty(rolecode)){
			this.deleteResourceByRolecode(rolecode);
		}
		
		//保存角色
		for (String resourcecode : resourcecodes) {
			if(!StringUtils.isEmpty(resourcecode)){
				rrr = new RoleRResource();
				rrr.setResourcecode(resourcecode);
				rrr.setRolecode(rolecode);
				userroleresourcemapper.saveRoleResource(rrr);
			}
		}
		return null;
	}

	public String deleteResourceByRolecode(String rolecode) {
		userroleresourcemapper.deleteResourceByRolecode(rolecode);
		return null;
	}

	public List findAllUserByRoleCode(String rolecode) {
		return userroleresourcemapper.findAllUserByRoleCode(rolecode);
	}

	public List findAllResourceByRoleCode(String rolecode) {
		return userroleresourcemapper.findAllResourceByRolecode(rolecode);
	}
}
