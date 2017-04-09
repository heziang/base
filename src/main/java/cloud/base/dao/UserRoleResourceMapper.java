package cloud.base.dao;

import java.util.List;
import java.util.Map;

public interface UserRoleResourceMapper {
	List<String> findAllResourcesByUserId(String userid);
	List<Map<String,String>> findAllResources();
}
