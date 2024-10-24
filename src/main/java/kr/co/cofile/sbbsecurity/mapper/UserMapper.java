package kr.co.cofile.sbbsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.cofile.sbbsecurity.domain.User;

@Mapper
public interface UserMapper {
	User findByUsername(String username);

}
