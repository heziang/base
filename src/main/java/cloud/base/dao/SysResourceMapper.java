package cloud.base.dao;

import java.util.List;
import java.util.Map;

import cloud.base.model.SysResource;

public interface SysResourceMapper {
	void save(SysResource sysResource);
	void update(SysResource sysResource);
	void delete(String[] ids);
	List<SysResource> search(Map conditions);
	String getTotal(Map conditions);
	SysResource getSysResourceById(String id);
}