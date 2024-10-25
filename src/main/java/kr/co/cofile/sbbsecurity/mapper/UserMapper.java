package kr.co.cofile.sbbsecurity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.cofile.sbbsecurity.domain.Role;
import kr.co.cofile.sbbsecurity.domain.User;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    void save(User user);
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
    List<Role> findRolesByUserId(@Param("userId") Long userId);
    User findById(@Param("id") Long id);
    List<Role> getAllRoles();
    List<User> findAll();
}