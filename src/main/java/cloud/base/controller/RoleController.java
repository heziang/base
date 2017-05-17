package cloud.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cloud.base.model.SysRole;
import cloud.base.model.VO.PageData;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private  ISysRoleService sysroleservice;
	
	@RequestMapping("/view")
	public String view(ModelMap modelMap, @PathVariable String userId){
//		SessionUser ua = (SessionUser)SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		return "/role/userInfo";
	}
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap,String groupcode){
		modelMap.addAttribute("groupcode", groupcode);
		return "/role/list";
	}
	@RequestMapping("/userselectrole/{userid}")
	public String userselectrole(ModelMap modelMap,@PathVariable("userid") String userid){
		modelMap.addAttribute("userid",userid);
		return "/role/userselectrole";
	}
	
	@RequestMapping("/search/list")
	public @ResponseBody PageData search(ModelMap modelMap,PageModel pageModel){
		
		Map cond = pageModel.initPageModel();
		sysroleservice.loadPageModel(pageModel);
		return pageModel.getPageData();
	}
	
	@RequestMapping("/add/{groupcode}")
	public String add(ModelMap modelMap,@PathVariable("groupcode") String groupcode){
		modelMap.addAttribute("groupcode",groupcode);
		return "/role/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(ModelMap modelMap,@PathVariable("id") String id){
		JSONObject jo = (JSONObject)JSON.toJSON(sysroleservice.loadResourceById(id));
		modelMap.addAttribute("info",jo.toJSONString());
		return "/role/edit";
	}
	
	/**
	 * 保存方法
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(ModelMap modelMap,SysRole role){
		String id = sysroleservice.saveSysRole(role);
		return id;
	}
	/**
	 * 更新方法
	 */
	@RequestMapping("/update")
	public @ResponseBody String update(ModelMap modelMap,SysRole role){
		String id = sysroleservice.updateSysRole(role);
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
				//如果角色被引用，则不允许删除
				if(sysroleservice.findAllResourceByRoleCode(id).size()>0||sysroleservice.findAllUserByRoleCode(id).size()>0)return "isRef";
			}
			sysroleservice.deleteSysRole(ids.split(","));
		}
		return null;
	}
	
	/**
	 * @param userid
	 * 		根据用户id得到对应的角色
	 * @return
	 */
	@RequestMapping("/getRolesByUserid/{userid}")
	public @ResponseBody List getRolesByUserid(@PathVariable("userid") String userid){
		return sysroleservice.findAllRoleByUserId(userid);
	}
	
	/**
	 * 保存资源
	 */
	@RequestMapping("/saveRoleResources")
	public  @ResponseBody String saveRoleResources(ModelMap modelMap,String rolecode, String resourcecodes){
		String[] resArray = resourcecodes.split(",");
		sysroleservice.saveRoleResources(rolecode, resArray);
		return null;
	}
}

