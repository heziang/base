package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import cloud.base.dao.SysRoleMapper;
import cloud.base.model.SysRole;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysRoleService;

@Transactional
@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper sysrolemapper;

	public SysRole loadResourceById(String id) {
		return sysrolemapper.loadUserById(id);
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

}
