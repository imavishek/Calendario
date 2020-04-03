/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.serviceImpl
 * @FileName: LoginService.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:17:29 pm
 */

package com.calendario.oauth.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendario.oauth.dto.LoginDetails;
import com.calendario.oauth.entities.User;
import com.calendario.oauth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (user == null) {
			log.info("Username Not Found");
			throw new UsernameNotFoundException(username);
		}

		return new LoginDetails(user.get());
	}

}
