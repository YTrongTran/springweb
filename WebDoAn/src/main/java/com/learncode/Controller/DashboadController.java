package com.learncode.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboad")
public class DashboadController {

	@GetMapping
	public String Default() {
		return "dashboad";
	}
}
