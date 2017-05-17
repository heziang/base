package cloud.base.dao;


import java.util.List;

import cloud.base.model.ResourceGroup;


public interface ResourceGroupMapper {
	List getListByPid(String pid);
	void save(ResourceGroup resourceGroup);
	void update(ResourceGroup resourceGroup);
	void delete(String[] ids);
	ResourceGroup getResourceGroupById(String id);
	int groupIsHaschildren(String groupid);
	int getResourceCountByGroupId(String groupid);
}