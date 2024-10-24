package kr.co.cofile.sbbsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.cofile.sbbsecurity.domain.User;

@Mapper
public interface UserMapper {

	void save(User user);
	User findByUsername(String username);
}
