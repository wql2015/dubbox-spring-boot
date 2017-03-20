package net.zero.controller.wellcome;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jsp视图测试
 * @author unique
 *
 */

@Controller
@RequestMapping("wellcome")
public class WellcomeController {
	
	@Value(value="${server.context-path}")
	private String contextPath;
	
	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", contextPath);
		return "wellcome";
	}
}
