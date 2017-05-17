package cloud.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cloud.base.model.SysResource;
import cloud.base.model.VO.PageData;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysResourceService;
import cloud.base.util.DictionaryUtil;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private  ISysResourceService sysresourceservice;
	
	@RequestMapping("/view")
	public String view(ModelMap modelMap, @PathVariable String userId){
//		SessionUser ua = (SessionUser)SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		return "/resource/view";
	}
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap,String groupcode){
		modelMap.addAttribute("groupcode", groupcode);
		return "/resource/list";
	}
	
	@RequestMapping("/search/list")
	public @ResponseBody PageData search(ModelMap modelMap,PageModel pageModel){
		
		Map cond = pageModel.initPageModel();
		sysresourceservice.loadPageModel(pageModel);
		List<SysResource> list = pageModel.getPageData().getRows();
		for (SysResource re : list) {
			re.setResourcetypename(DictionaryUtil.getNameByTypeAndType("resourceType", re.getResourcetype()));
		}
		return pageModel.getPageData();
	}
	
	@RequestMapping("/roleselectresource/{rolecode}")
	public String roleselectresource(ModelMap modelMap,@PathVariable("rolecode") String rolecode){
		modelMap.addAttribute("rolecode",rolecode);
		return "/resource/roleselectresource";
	}
	
	@RequestMapping("/add/{groupcode}")
	public String add(ModelMap modelMap,@PathVariable("groupcode") String groupcode){
		modelMap.addAttribute("groupcode",groupcode);
		return "/resource/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap modelMap,@PathVariable("id") String id){
		JSONObject jo = (JSONObject)JSON.toJSON(sysresourceservice.loadResourceById(id));
		modelMap.addAttribute("info",jo.toJSONString());
		return "/resource/edit";
	}
	
	/**
	 * 保存方法
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(ModelMap modelMap,SysResource resource){
		String id = sysresourceservice.saveSysResource(resource);
		return id;
	}
	/**
	 * 更新方法
	 */
	@RequestMapping("/update")
	public @ResponseBody String update(ModelMap modelMap,SysResource resource){
		String id = sysresourceservice.updateSysResource(resource);
		return id;
	}
	/**
	 * 删除方法
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(String ids){
		if(!StringUtils.isEmpty(ids)){
			String[] idsArray = ids.split(",");
			for (String id : idsArray) {
				//如果资源被引用，则不允许删除
				List list = sysresourceservice.findAllRoleByResourceCode(id);
				if(list.size()>0){
					return "isRef";
				}
			}
			//如果资源没有被引用，删除
			sysresourceservice.deleteSysResource(ids.split(","));
		}
		return null;
	}
	/**
	 * @param userid
	 * 		根据角色id得到对应的资源
	 * @return
	 */
	@RequestMapping("/getResourcesByRoleCode/{rolecode}")
	public @ResponseBody List getResourcesByRoleCode(@PathVariable("rolecode") String rolecode){
		return sysresourceservice.findAllResourceByRoleCode(rolecode);
	}
}

