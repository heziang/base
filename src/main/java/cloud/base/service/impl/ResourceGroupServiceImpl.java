package cloud.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import cloud.base.dao.ResourceGroupMapper;
import cloud.base.model.ResourceGroup;
import cloud.base.service.IResourceGroupService;

@Transactional
@Service
public class ResourceGroupServiceImpl implements IResourceGroupService {

	@Autowired
	private ResourceGroupMapper usergroupmapper;
	
	public List getListByPid(String pid) {
		return usergroupmapper.getListByPid(pid);
	}

	public void save(ResourceGroup resourceGroup) {
		usergroupmapper.save(resourceGroup);
	}

	public void update(ResourceGroup resourceGroup) {
		usergroupmapper.update(resourceGroup);
		
	}

	public void delete(String[] ids) {
		usergroupmapper.delete(ids);
	}

	public ResourceGroup getResourceGroupById(String id) {
		return usergroupmapper.getResourceGroupById(id);
	}

	public int groupIsHaschildren(String groupid) {
		return usergroupmapper.groupIsHaschildren(groupid);
	}
}
