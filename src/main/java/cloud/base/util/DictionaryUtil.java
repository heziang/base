package cloud.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cloud.base.model.Dictionary;
import cloud.base.service.IDictionaryService;

public class DictionaryUtil {
	public static  Map<String,Map<String,String>> dictionaryMap;//map的键为数据字典的type，值为对应类型的code和name
	
	public static void initDictionaryMap(){
		dictionaryMap = new HashMap<String, Map<String,String>>();
		//获取所有字典记录
		IDictionaryService service = SpringUtil.getBean("dictionaryserviceimpl",IDictionaryService.class);
		List<Dictionary> list = service.getAllDict();
		//初始化集合
		for(Dictionary dic :list){
			Map<String,String> dicMap = dictionaryMap.get(dic.getTypecode());
			if(dicMap == null){
				dicMap = new HashMap<String,String>();
			}
			dicMap.put(dic.getDcode(), dic.getDname());
			dictionaryMap.put(dic.getTypecode(), dicMap);
		}
	}
	
	public static String getNameByTypeAndType(String type,String code){
		//如果集合没初始化，或者由键得到的对象不存在（有数据差异），重新初始化集合
		if(dictionaryMap==null||dictionaryMap.get(type)==null||dictionaryMap.get(type).get(code)==null){
			initDictionaryMap();
		}
		if(dictionaryMap!=null&&dictionaryMap.get(type)!=null){
			return dictionaryMap.get(type).get(code);
		}
		return null;
	}
}
