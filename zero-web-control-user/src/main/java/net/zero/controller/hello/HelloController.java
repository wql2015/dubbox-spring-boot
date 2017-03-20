package net.zero.controller.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	//加载配置文件中的参数  
    @Value(value="${ljq.name}")  
    private String name;  
	
	@RequestMapping(value = "sayHello", method = RequestMethod.POST)
	public Object sayHello(@RequestBody ModelMap modelMap) {
		String userName = (String) modelMap.get("userName");
		
		return userName + " say hello!";
		
	}
	
	@RequestMapping(value = "getPropertis", method = RequestMethod.GET)
	public String getPropertis() {
		return name;
	}
	

}
