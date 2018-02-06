package cloud.base.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cloud.base.model.VO.PageData;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysLogService;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

	@Autowired
	private  ISysLogService sysLogService;
	
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/syslog/list";
	}
	
	@RequestMapping("/search/list")
	public @ResponseBody PageData search(ModelMap modelMap,PageModel pageModel){
		Map cond = pageModel.initPageModel();
		cond.put("endDate", cond.get("endDate")+" 23:59:59");
		sysLogService.loadPageModel(pageModel);
		return pageModel.getPageData();
	}

}

