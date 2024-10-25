package kr.co.cofile.sbbsecurity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
	
	@GetMapping("/signup")
	public String singup() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(true);
		
		// 사용자 저장 <= user
		// useGeneratedKeys="true" keyProperty="id" 맵퍼 xml에 추가해야 함!!!
		userMapper.save(user);
		// 권한 부여 ROLE_USER
		userMapper.insertUserRole(user.getId(), 1L);
		
		return "redirect:/login";
	}
}
