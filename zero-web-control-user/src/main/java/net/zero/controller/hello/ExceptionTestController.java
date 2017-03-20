package net.zero.controller.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zero.common.exceptions.web.WebJsonException;

@Controller
@RequestMapping("/exceptions")
public class ExceptionTestController {

	@RequestMapping("/helloException")
	public String hello() throws Exception {
		throw new Exception("发生错误");
	}

	@RequestMapping("/json")
	public String json() throws WebJsonException {
		throw new WebJsonException("发生错误2");
	}
	
	@RequestMapping("/to404")
	public ModelAndView to404() throws Exception {
		ModelAndView view = new ModelAndView("notFound");
		return view;
	}

}
