package cloud.base.dao;


import java.util.List;
import java.util.Map;

import cloud.base.model.SysLog;


public interface SysLogMapper {
	List<SysLog> search(Map conditions);
	void save(SysLog sysLog);
	void saveMap(Map map);
	String getTotal(Map conditions);
}