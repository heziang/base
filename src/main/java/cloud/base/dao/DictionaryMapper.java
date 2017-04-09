package cloud.base.dao;

import java.util.List;


public interface DictionaryMapper {
	List getAllDictByTypecode(String typecode);
}