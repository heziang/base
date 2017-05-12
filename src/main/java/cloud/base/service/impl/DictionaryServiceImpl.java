package cloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import cloud.base.dao.DictionaryMapper;
import cloud.base.dao.SysUserMapper;
import cloud.base.service.IDictionaryService;

@Transactional
@Service("dictionaryserviceimpl")
public class DictionaryServiceImpl implements IDictionaryService {
	
	@Autowired
	private DictionaryMapper dictionarymapper;
	
	public List getAllDictByTypecode(String typecode) {
		return dictionarymapper.getAllDictByTypecode(typecode);
	}

	public List getAllDict() {
		return dictionarymapper.getAllDict();
	}
}
