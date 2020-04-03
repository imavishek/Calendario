/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.serviceImpl
 * @FileName: LoginServiceTest.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 1:04:31 am
 */

package com.calendario.oauth.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.calendario.oauth.entities.User;
import com.calendario.oauth.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class LoginServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private LoginService loginService;

	@Test
	public void loadUserByUsernameTest() {

		String username = "avishek.akd@gmail.com";

		// For Success case
		Optional<User> user = Optional.of(new User());
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		UserDetails userDetails = loginService.loadUserByUsername(username);

		assertNotNull(userDetails);

		// For Failure case
		when(userRepository.findByUsername(anyString())).thenReturn(null);

		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			loginService.loadUserByUsername(username);
		});
	}
}
