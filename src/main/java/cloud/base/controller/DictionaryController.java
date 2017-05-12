package cloud.base.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloud.base.service.IDictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private  IDictionaryService dictionaryservice;
	
	@RequestMapping("/searchByTypecode/{typecode}")
	public @ResponseBody List search(@PathVariable("typecode") String typecode){
		
		return dictionaryservice.getAllDictByTypecode(typecode);
	}
	
}

