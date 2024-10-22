package kr.co.cofile.sbbsecurity.mapper;

import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    void save(User user);
    void insertUserRole(Long userId, Long roleId);
    List<Role> findRolesByUserId(Long userId);
    User findById(Long id);
    List<Role> getAllRoles();
    List<User> findAll();
}