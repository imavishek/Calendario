/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.serviceImpl
 * @FileName: UserServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:21:21 pm
 */

package com.calendario.oauth.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendario.oauth.dto.PasswordDto;
import com.calendario.oauth.entities.User;
import com.calendario.oauth.repository.UserRepository;
import com.calendario.oauth.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Boolean savePassword(PasswordDto passwordDto) {

		Boolean success = false;

		User user = new User();
		user.setUserId(UUID.fromString(passwordDto.getUserId()));
		user.setUsername(passwordDto.getUsername());
		user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

		user = userRepository.save(user);

		if (user != null) {
			log.info("Password saved successfully. UserId: " + user.getUserId());

			success = true;
		}

		return success;
	}

}
