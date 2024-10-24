package kr.co.cofile.sbbsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/hello")
	public String hello() {
		// hello.html <= thymeleaf 역할
		// templates
		return "hello";
	}
}
