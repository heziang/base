package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.base.dao.SysLogMapper;
import cloud.base.model.SysLog;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysLogService;


@Service
public class SysLogServiceImpl implements ISysLogService {
	
	@Autowired
	private SysLogMapper sysusermapper;

	@Override
	public List<SysLog> search(Map conditions) {
		return sysusermapper.search(conditions);
	}

	@Override
	public void save(SysLog sysLog) {
		sysusermapper.save(sysLog);		
	}

	@Override
	public void save(Map<String, Object> map) {
		sysusermapper.saveMap(map);
	}
	
	public PageModel loadPageModel(PageModel pageModel) {
		pageModel.getPageData().setTotal(this.getTotals(pageModel.getConditions()));
		pageModel.getPageData().setRows(this.search(pageModel.getConditions()));
		return pageModel;
	}

	@Override
	public String getTotals(Map conditions) {
		return sysusermapper.getTotal(conditions);
	}
}
