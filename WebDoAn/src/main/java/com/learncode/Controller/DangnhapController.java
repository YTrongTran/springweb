package com.learncode.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dangnhap")
public class DangnhapController {

	@GetMapping("/")
	public String Default() {
		return "/Web/dangnhap";
		
	}
	@PostMapping
	public String XylyDangNhap(@RequestParam String tendangnhap,@RequestParam String matkhau) {
		if(tendangnhap.equals("Trantrongy") && matkhau.equals("1234")) {
			return "redirect:/";
		}else {
			return "/Web/dangnhap";
		}
		
	}
}
