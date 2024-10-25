package kr.co.cofile.sbbsecurity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.cofile.sbbsecurity.domain.Role;
import kr.co.cofile.sbbsecurity.domain.User;

@Mapper
public interface UserMapper {

	void save(User user);
	void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
	User findByUsername(String username);
	List<User> findAll();
	List<Role> findRolesByUserId(Long userId);
    User findById(Long id);
    List<Role> getAllRoles();
}
