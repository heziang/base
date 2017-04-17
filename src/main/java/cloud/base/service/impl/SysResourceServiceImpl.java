package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import cloud.base.dao.SysResourceMapper;
import cloud.base.model.SysResource;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysResourceService;

@Transactional
@Service
public class SysResourceServiceImpl implements ISysResourceService {
	@Autowired
	private SysResourceMapper sysrolemapper;

	public SysResource loadResourceById(String id) {
		return sysrolemapper.loadUserById(id);
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

	public String deleteSysResource(String[] ids) {
		sysrolemapper.delete(ids);
		return null;
	}

}
