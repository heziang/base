package cloud.base.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cloud.base.model.SessionUser;
import cloud.base.model.SysResource;
import cloud.base.model.SysUser;
import cloud.base.model.Userinfo;
import cloud.base.model.VO.PageData;
import cloud.base.model.VO.PageModel;
import cloud.base.service.ISysUserService;
import cloud.base.util.SecurityUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private  ISysUserService sysUserService;
	
	@RequestMapping("/view")
	public String view(ModelMap modelMap, @PathVariable String userId){
//		SessionUser ua = (SessionUser)SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		return "/user/userInfo";
	}
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap,String groupcode){
		modelMap.addAttribute("groupcode", groupcode);
		return "/user/list";
	}
	
	@RequestMapping("/search/list")
	public @ResponseBody PageData search(ModelMap modelMap,PageModel pageModel){
		
		Map cond = pageModel.initPageModel();
		sysUserService.loadPageModel(pageModel);
		return pageModel.getPageData();
	}
	
	@RequestMapping("/add/{groupcode}")
	public String add(ModelMap modelMap,@PathVariable("groupcode") String groupcode){
		modelMap.addAttribute("groupcode",groupcode);
		return "/user/add";
	}
	
	@RequestMapping("/edit/{userid}")
	public String edit(ModelMap modelMap,@PathVariable("userid") String userid){
		JSONObject jo = (JSONObject)JSON.toJSON(sysUserService.getUserinfoById(userid));
		modelMap.addAttribute("info",jo.toJSONString());
		return "/user/edit";
	}
	
	@RequestMapping("/get/{userid}")
	public @ResponseBody SysUser getUserById(ModelMap modelMap,@PathVariable("userid") String userid){
		SysUser u = sysUserService.loadUserById(userid);
		if(u!=null){
			u.setPwd("-");
		}
		return u;
	}
	/**
	 * 保存方法
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(ModelMap modelMap,SysUser user,Userinfo userinfo){
		String id = sysUserService.saveSysUser(user,userinfo);
		return id;
	}
	/**
	 * 更新方法
	 */
	@RequestMapping("/update")
	public @ResponseBody String update(ModelMap modelMap,SysUser user,Userinfo userinfo){
		String id = sysUserService.updateUserinfo(user,userinfo);
		return id;
	}
	/**
	 * 删除方法
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(String userids){
		sysUserService.deleteSysUser(userids.split(","));
		return null;
	}
	/**
	 * 保存角色方法
	 */
	@RequestMapping("/saveRole")
	public  @ResponseBody String saveRole(ModelMap modelMap,String userid,String rolecodes){
		String[] codeArray = rolecodes.split(",");
		sysUserService.saveUserRole(userid, codeArray);
		return null;
	}
	
	/**
	 * @param modelMap
	 * @param userid
	 * 		得到某个用户的资源map集合
	 * @return
	 */
	@RequestMapping("/findResourceByUserid/{userid}")
	public  @ResponseBody Map findResourceByUserid(ModelMap modelMap,@PathVariable("userid")String userid){
		Map map = new LinkedHashMap();
		List<SysResource> list = sysUserService.getAllResourcesByUserId(userid);
		
		for (SysResource sysResource : list) {
			if(sysResource!=null){
				//只处理url类型的资源
				if("u".equals(sysResource.getResourcetype())){
					//得到菜单的组
					String resourcegroupname = sysResource.getGroupname();
					//对应组的菜单集合
					List<SysResource> meauList = null;
					//如果集合里没有放入菜单组的信息，则生成
					if(map.get(resourcegroupname)==null){
						meauList = new LinkedList<SysResource>();
					}else{
						meauList = (List<SysResource>) map.get(resourcegroupname);
					}
					meauList.add(sysResource);
					map.put(resourcegroupname, meauList);
				}
			}
		}
		return map;
	}
	
	/**
	 * @param modelMap
	 * 		得到当前用户的资源map集合
	 * @return
	 */
	@RequestMapping("/findUserResource")
	public  @ResponseBody Map findUserResource(ModelMap modelMap){
		Map map = new LinkedHashMap();
		SessionUser su = SecurityUtil.getSessionUser();
		if(su==null){
			return map;
		}
		List<SysResource> list = sysUserService.getAllResourcesByUserId(su.getUsername());
		
		for (SysResource sysResource : list) {
			if(sysResource!=null){
				//只处理url类型的资源
				if("u".equals(sysResource.getResourcetype())){
					//得到菜单的组
					String resourcegroupname = sysResource.getGroupname();
					//对应组的菜单集合
					List<SysResource> meauList = null;
					//如果集合里没有放入菜单组的信息，则生成
					if(map.get(resourcegroupname)==null){
						meauList = new LinkedList<SysResource>();
					}else{
						meauList = (List<SysResource>) map.get(resourcegroupname);
					}
					meauList.add(sysResource);
					map.put(resourcegroupname, meauList);
				}
			}
		}
		return map;
	}
}

