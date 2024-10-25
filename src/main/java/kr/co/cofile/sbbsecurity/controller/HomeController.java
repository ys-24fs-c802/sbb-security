package kr.co.cofile.sbbsecurity.controller;

import kr.co.cofile.sbbsecurity.domain.Role;
import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password) {

        // 사용자 생성
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);

        // 사용자 저장
        userMapper.save(user);

        // ROLE_USER 권한 추가 (roles 테이블에서 ROLE_USER의 id가 1이라고 가정)
        userMapper.insertUserRole(user.getId(), 1L);

        return "redirect:/login";
    }

    // 사용자 목록
    @GetMapping("/users")
    public String userList(Model model) {
        List<User> users = userMapper.findAll(); // findAll 메서드 추가 필요
        model.addAttribute("users", users);
        return "user-list";
    }

    // 사용자의 권한 목록 조회
    @GetMapping("/user/{userId}/roles")
    public String userRoles(@PathVariable("userId") Long userId, Model model) {
        List<Role> roles = userMapper.findRolesByUserId(userId);
        String username = userMapper.findById(userId).getUsername();

        model.addAttribute("roles", roles);
        model.addAttribute("username", username);
        model.addAttribute("userId", userId);

        return "user-roles";
    }

    // 선택적: 권한 추가 처리
    @PostMapping("/user/{userId}/role/add")
    public String addRole(@PathVariable("userId") Long userId, @RequestParam("roleId") Long roleId) {
        userMapper.insertUserRole(userId, roleId);
        return "redirect:/user/%d/roles".formatted(userId);
    }

    @GetMapping("/")
    public String home() {
        log.info("home controller...");
        return "home";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
