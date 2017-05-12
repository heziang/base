package cloud.base.dao;

import java.util.List;
import java.util.Map;

import cloud.base.model.RoleRResource;
import cloud.base.model.SysResource;
import cloud.base.model.SysRole;
import cloud.base.model.UserRRole;

public interface UserRoleResourceMapper {
	List<String> findAllRolesByUserId(String userid);
	List<Map<String,String>> findAllResources();
	void saveUserRole(UserRRole userrrole);
	void saveRoleResource(RoleRResource rolerresource);
	void deleteUserRole(UserRRole userrrole);
	void deleteRoleResource(RoleRResource rolerresource);
	void deleteRoleByUserId(String userid);
	void deleteResourceByRolecode(String rolecode);
	List<SysRole> findAllRoleByUserId(String userid);
	List<SysResource> findAllResourceByRolecode(String rolecode);
	List getAllResourcesByUserId(String userid);
}
