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

import cloud.base.model.ResourceGroup;
import cloud.base.model.VO.EasyUITreeVO;
import cloud.base.service.IResourceGroupService;

@Controller
@RequestMapping("/resourcegroup")
public class ResourceGroupController {

	@Autowired
	private  IResourceGroupService resourcegroupservice;
	
	@RequestMapping("/searchByParent")
	private @ResponseBody List getListByPid(String id){
 		if(StringUtils.isEmpty(id))id = "0";
		List<ResourceGroup> list = resourcegroupservice.getListByPid(id);
		List<EasyUITreeVO> volist = new LinkedList<EasyUITreeVO>();
		for (ResourceGroup userGroup : list) {
			EasyUITreeVO vo = new EasyUITreeVO();
			vo.setId(userGroup.getGroupcode());
			vo.setText(userGroup.getGroupname());
			int childrenNum = resourcegroupservice.groupIsHaschildren(userGroup.getGroupcode());
			if(childrenNum==0){
				vo.setState("open");
			}else{
				vo.setState("closed");
			}
			
			volist.add(vo);
		}
		return volist;
	}
	
	@RequestMapping("/add/{pid}")
	public String add(ModelMap modelMap,@PathVariable("pid") String pid){
		modelMap.addAttribute("pid", pid);
		return "/resourcegroup/add";
	}
	
	@RequestMapping("/tree")
	public String tree(){
		return "/resourcegroup/resourcegrouptree";
	}
	
	@RequestMapping("/edit/{groupcode}")
	public String edit(ModelMap modelMap,@PathVariable("groupcode") String groupcode){
		JSONObject jo = (JSONObject)JSON.toJSON(resourcegroupservice.getResourceGroupById(groupcode));
		modelMap.addAttribute("info",jo.toJSONString());
		return "/resourcegroup/edit";
	}
	
	/**
	 * 保存方法
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(ModelMap modelMap,ResourceGroup resourceGroup){
		resourcegroupservice.save(resourceGroup);
		return null;
	}
	/**
	 * 更新方法
	 */
	@RequestMapping("/update")
	public @ResponseBody String update(ModelMap modelMap,ResourceGroup resourceGroup){
		resourcegroupservice.update(resourceGroup);
		return null;
	}
	/**
	 * 删除方法
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(String groupids){
		resourcegroupservice.delete(groupids.split(","));
		return null;
	}
}

