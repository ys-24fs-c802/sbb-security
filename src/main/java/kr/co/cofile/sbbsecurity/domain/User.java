package kr.co.cofile.sbbsecurity.domain;

import java.util.List;

import lombok.Data;

// Data is like having implicit @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor annotations on the class (except that no constructor will be generated if any explicitly written constructors already exist).
@Data
public class User {
	private Long id;
	private String username;
	private String password;
	private boolean enabled;
//	private String role;
	private List<Role> roles;
}
