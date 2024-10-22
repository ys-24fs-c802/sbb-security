package kr.co.cofile.sbbsecurity.service;

import kr.co.cofile.sbbsecurity.domain.Role;
import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);

        userMapper.save(user);
        userMapper.insertUserRole(user.getId(), 1L); // ROLE_USER
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
        return userMapper.findRolesByUserId(userId);
    }

    @Override
    public void addUserRole(Long userId, Long roleId) {
        userMapper.insertUserRole(userId, roleId);
    }
}
