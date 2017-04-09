package cloud.base.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cloud.base.model.UserGroup;
import cloud.base.model.VO.EasyUITreeVO;
import cloud.base.service.IUserGroupService;

@Controller
@RequestMapping("/usergroup")
public class UserGroupController {

	@Autowired
	private  IUserGroupService usergroupservice;
	
	@RequestMapping("/searchByParent/{pid}")
	private @ResponseBody List getListByPid(@PathVariable("pid") String pid){
 		if(StringUtils.isEmpty(pid))pid = "0";
		List<UserGroup> list = usergroupservice.getListByPid(pid);
		List<EasyUITreeVO> volist = new LinkedList<EasyUITreeVO>();
		for (UserGroup userGroup : list) {
			EasyUITreeVO vo = new EasyUITreeVO();
			vo.setId(userGroup.getGroupcode());
			vo.setText(userGroup.getGroupname());
			vo.setState("closed");
			volist.add(vo);
		}
		return volist;
	}
	
	@RequestMapping("/add/{pid}")
	public String add(ModelMap modelMap,@PathVariable("pid") String pid){
		modelMap.addAttribute("pid", pid);
		return "/usergroup/add";
	}
	
	@RequestMapping("/tree/{pid}")
	public String tree(ModelMap modelMap,@PathVariable("pid") String pid){
		modelMap.addAttribute("pid", pid);
		return "/usergroup/usergrouptree";
	}
	
	@RequestMapping("/edit/{groupcode}")
	public String edit(ModelMap modelMap,@PathVariable("groupcode") String groupcode){
		JSONObject jo = (JSONObject)JSON.toJSON(usergroupservice.getUserGroupById(groupcode));
		modelMap.addAttribute("info",jo.toJSONString());
		return "/user/edit";
	}
	
	/**
	 * 保存方法
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(ModelMap modelMap,UserGroup ug){
		usergroupservice.save(ug);
		return null;
	}
	/**
	 * 更新方法
	 */
	@RequestMapping("/update")
	public @ResponseBody String update(ModelMap modelMap,UserGroup ug){
		usergroupservice.update(ug);
		return null;
	}
	/**
	 * 删除方法
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(String userids){
		usergroupservice.delete(userids.split(","));
		return null;
	}
}

