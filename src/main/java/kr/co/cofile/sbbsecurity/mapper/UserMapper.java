package kr.co.cofile.sbbsecurity.mapper;

import kr.co.cofile.sbbsecurity.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);

    void save(User user);
}