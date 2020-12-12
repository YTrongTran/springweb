package com.learncode.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		return "Login";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successPage() {
		return "layouts/index";
	}
	
	
	
}
