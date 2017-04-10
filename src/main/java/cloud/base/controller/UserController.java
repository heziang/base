package cloud.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cloud.base.model.SessionUser;
import cloud.base.model.SysUser;
import cloud.base.model.Userinfo;
import cloud.base.service.ISysUserService;

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
	public @ResponseBody Map search(ModelMap modelMap,Integer page,Integer rows){
		
		Map conditions = new HashMap();
		conditions.put("start", (page-1)*rows);
		conditions.put("rows", rows);
		String total = sysUserService.getTotals(conditions);
		List list = sysUserService.search(conditions);
		Map resMap = new HashMap();
		resMap.put("total", total);
		resMap.put("rows", list);
		return resMap;
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap){
		return "/user/add";
	}
	
	@RequestMapping("/edit/{userid}")
	public String edit(ModelMap modelMap,@PathVariable("userid") String userid){
		JSONObject jo = (JSONObject)JSON.toJSON(sysUserService.getUserinfoById(userid));
		modelMap.addAttribute("info",jo.toJSONString());
		return "/user/edit";
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
}

