package kr.co.cofile.sbbsecurity;

import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;  // 사용자 추가를 위한 매퍼

    @Test
    @DisplayName("사용자 추가 테스트")
    void addUser() {
        // 사용자 생성
        User user = new User();
        user.setUsername("user2");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setEnabled(true);

        // 사용자 저장
        userMapper.save(user);

        // 사용자에게 ROLE_USER 권한 추가
        userMapper.insertUserRole(user.getId(), 1L); // 1L은 ROLE_USER의 ID

        // 저장된 사용자 조회
        User savedUser = userMapper.findByUsername("user2");

        assertNotNull(savedUser);
        assertNotNull(savedUser.getRoles());
        assertFalse(savedUser.getRoles().isEmpty());

        log.info("Created user: {}", savedUser);
        log.info("User roles: {}", savedUser.getRoles());
    }

    @Test
    @DisplayName("관리자 사용자 추가 테스트")
    void addAdminUser() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEnabled(true);

        userMapper.save(admin);

        // 관리자에게 여러 권한 추가
        userMapper.insertUserRole(admin.getId(), 1L); // ROLE_USER
        userMapper.insertUserRole(admin.getId(), 2L); // ROLE_ADMIN

        User savedAdmin = userMapper.findByUsername("admin");
        assertNotNull(savedAdmin);
        assertEquals(2, savedAdmin.getRoles().size());

        log.info("Created admin: {}", savedAdmin);
        log.info("Admin roles: {}", savedAdmin.getRoles());
    }
}