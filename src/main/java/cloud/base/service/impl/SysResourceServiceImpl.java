package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;









import cloud.base.annotation.SystemLog;
import cloud.base.dao.SysResourceMapper;
import cloud.base.dao.UserRoleResourceMapper;
import cloud.base.model.SysResource;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysResourceService;

@Transactional
@Service
public class SysResourceServiceImpl implements ISysResourceService {
	@Autowired
	private SysResourceMapper sysrolemapper;
	
	@Autowired
	private UserRoleResourceMapper userroleresourcemapper;
	
	public SysResource loadResourceById(String id) {
		return sysrolemapper.getSysResourceById(id);
	}

	public List<SysResource> search(Map conditions) {
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

	public String saveSysResource(SysResource resource) {
		sysrolemapper.save(resource);
		return null;
	}

	public String updateSysResource(SysResource resource) {
		sysrolemapper.update(resource);
		return null;
	}
	@SystemLog(description="删除资源",module="资源管理")
	public String deleteSysResource(String[] ids) {
		sysrolemapper.delete(ids);
		return null;
	}

	public List findAllResourceByRoleCode(String rolecode) {
		return userroleresourcemapper.findAllResourceByRolecode(rolecode);
	}

	public List findAllRoleByResourceCode(String resourcecode) {
		return userroleresourcemapper.findAllRoleByResourceCode(resourcecode);
	}

}
