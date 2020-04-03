/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.serviceImpl
 * @FileName: UserServiceImplTest.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 1:09:07 am
 */

package com.calendario.oauth.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.calendario.oauth.dto.PasswordDto;
import com.calendario.oauth.entities.User;
import com.calendario.oauth.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void savePasswordTest() {

		PasswordDto passwordDto = new PasswordDto();
		passwordDto.setUserId("12fef224-7665-41bd-bc26-cb5dc71af82d");
		passwordDto.setUsername("avishek.akd@gmail.com");
		passwordDto.setPassword("BYmalZGHvsPm/dUXipM0FkTcfq8oUICt2tvX+HBZjO0=");

		// For Success case
		when(passwordEncoder.encode(anyString())).thenReturn("encodeddPassword");
		when(userRepository.save(any(User.class))).thenReturn(new User());

		Boolean success = userService.savePassword(passwordDto);

		assertEquals(true, success);

		// For Failure case
		when(userRepository.save(any(User.class))).thenReturn(null);

		success = userService.savePassword(passwordDto);

		assertEquals(false, success);

	}
}
