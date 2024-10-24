package kr.co.cofile.sbbsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
	
	@PostMapping("/login")
	public void login(@ModelAttribute User user) {
		// 클라이언트에서 전달 받은 사용자
		log.info(user.toString());
		// 디비에 등록되어 있는 사용자
		User member = userMapper.findByUsername(user.getUsername());
		if (user.getPassword().equals(member.getPassword())) {
			log.info("회원");
		} else {
			log.info("비회원");
		}
	}
}
