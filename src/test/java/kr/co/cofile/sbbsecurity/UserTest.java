package kr.co.cofile.sbbsecurity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.cofile.sbbsecurity.domain.User;
import kr.co.cofile.sbbsecurity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	@DisplayName("사용자 추가 테스트")
	void addUser()  {
		User user = new User();
		user.setUsername("test");
		user.setPassword("1234");
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		
		log.info("암호화하지 않음: ", user.getPassword());
		// 맵퍼를 이용해 데이터를 저장한다.
		userMapper.save(user);
	}
	

	
}
