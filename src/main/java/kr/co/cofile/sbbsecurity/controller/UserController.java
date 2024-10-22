package kr.co.cofile.sbbsecurity.controller;

import kr.co.cofile.sbbsecurity.domain.Role;
import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password) {
        userService.signup(username, password);
        return "redirect:/login";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/{userId}/roles")
    public String userRoles(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        List<Role> roles = userService.getUserRoles(userId);

        model.addAttribute("roles", roles);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userId", userId);

        return "user-roles";
    }

    @PostMapping("/{userId}/role/add")
    public String addRole(@PathVariable Long userId, @RequestParam Long roleId) {
        userService.addUserRole(userId, roleId);
        return "redirect:/user/%d/roles".formatted(userId);
    }
}