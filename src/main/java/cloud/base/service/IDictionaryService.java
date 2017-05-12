package cloud.base.service;

import java.util.List;


public interface IDictionaryService {
	/**
	 * @param typecode
	 * 		根据字典类型查找字典
	 * @return
	 */
	List getAllDictByTypecode(String typecode);
	
	/**
	 * @return
	 * 	获取所有字典记录
	 */
	List getAllDict();
}
