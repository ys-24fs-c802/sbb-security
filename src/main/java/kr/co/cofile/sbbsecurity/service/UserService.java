package kr.co.cofile.sbbsecurity.service;

import kr.co.cofile.sbbsecurity.domain.Role;
import kr.co.cofile.sbbsecurity.domain.User;

import java.util.List;

public interface UserService {
    void signup(String username, String password);
    List<User> getAllUsers();
    User getUserById(Long userId);
    List<Role> getUserRoles(Long userId);
    void addUserRole(Long userId, Long roleId);
}