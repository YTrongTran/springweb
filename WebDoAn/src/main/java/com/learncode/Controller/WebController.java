package com.learncode.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/web")
public class WebController {

	@RequestMapping("/")
	public String home() {
		return "web/giaodien";
	}
}
