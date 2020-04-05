/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.serviceImpl
 * @FileName: UserService.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:37:08 pm
 */

package com.calendario.user.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.global.common.microservice.exceptions.CalendarioInvalidTokenException;
import com.calendario.global.common.microservice.exceptions.CalendarioUserEmailExistsException;
import com.calendario.user.dto.ActiveProfileDto;
import com.calendario.user.dto.PasswordDto;
import com.calendario.user.dto.UserRegisterDto;
import com.calendario.user.entities.User;
import com.calendario.user.producer.RegistrationMailProducer;
import com.calendario.user.producer.RegistrationSuccessMailProducer;
import com.calendario.user.properties.MessageProperties;
import com.calendario.user.properties.TimeProperties;
import com.calendario.user.properties.UrlProperties;
import com.calendario.user.repository.UserRepository;
import com.calendario.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@RefreshScope
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RegistrationMailProducer registrationMailProducer;

	@Autowired
	private RegistrationSuccessMailProducer registrationSuccessMailProducer;

	@Autowired
	private MessageProperties messageProperties;

	@Autowired
	private TimeProperties timeProperties;

	@Autowired
	private UrlProperties urlProperties;

	@Override
	public Boolean register(UserRegisterDto userRegisterDto)
			throws CalendarioUserEmailExistsException, CalendarioBadRequestApiException {
		Boolean success = false;

		// Checking the existence of Email
		if (getUserByEmail(userRegisterDto.getEmail()) != null) {
			log.info("User email exists: " + userRegisterDto.getEmail());
			throw new CalendarioUserEmailExistsException(
					messageProperties.getEmail_exist() + " EmailId: " + userRegisterDto.getEmail());
		}

		User user = new User();
		user.setEmail(userRegisterDto.getEmail());
		user.setName(userRegisterDto.getName());
		user.setToken(UUID.randomUUID());
		user.setTokenGeneratedDate(LocalDateTime.now());

		user = userRepository.save(user);

		if (user == null) {
			return success;
		}

		// Send mail to user
		registrationMailProducer.sendMail(user);

		log.info("User registered successfully. UserId: " + user.getUserId());

		success = true;

		return success;
	}

	@Override
	public Boolean isValidToken(UUID token, Duration expiration) {
		Boolean success = false;

		User user = userRepository.findByToken(token).orElse(null);

		if (user == null) {
			return success;
		}

		// Validation token expiration time
		Long diff = LocalDateTime.from(user.getTokenGeneratedDate()).until(LocalDateTime.now(), ChronoUnit.MILLIS);

		if (diff >= expiration.toMillis()) {
			return success;
		}

		success = true;

		return success;
	}

	@Override
	public Boolean activeProfile(ActiveProfileDto activeProfileDto)
			throws CalendarioInvalidTokenException, URISyntaxException, CalendarioBadRequestApiException {
		Boolean success = false;
		UUID token = activeProfileDto.getToken();
		Duration expiration = timeProperties.getExpiration_activeaccount();

		// Check the token validation
		Boolean isValid = isValidToken(token, expiration);
		if (!isValid) {
			log.info("Invalid token for active account. Token: " + token);
			throw new CalendarioInvalidTokenException(messageProperties.getInvalid_token() + " Token: " + token);
		}

		// Get the user by userid
		User user = userRepository.findByToken(token).get();

		// save the password
		PasswordDto passwordDto = new PasswordDto();
		passwordDto.setUserId(user.getUserId());
		passwordDto.setUsername(user.getEmail());
		passwordDto.setPassword(activeProfileDto.getPassword());

		Boolean isPasswordSaved = savePassword(passwordDto);

		if (isPasswordSaved) {
			// Active the profile and
			user.setActive((byte) 1);
			user.setToken(null);
			user.setTokenGeneratedDate(null);
			user = userRepository.save(user);

			// Send mail to user
			registrationSuccessMailProducer.sendMail(user);

			if (user != null) {

				log.info("User activated successfully UserId: " + user.getUserId());

				success = true;
			}
		}

		return success;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	private Boolean savePassword(PasswordDto passwordDto) throws URISyntaxException {

		// OAuth server save password url
		URI url = new URI(urlProperties.getOauth_server_save_password());
		Response<Object> response = null;

		response = restTemplate.postForObject(url, passwordDto, Response.class);

		if (response.getCode().equals(Status.C_1.getCode())) {
			log.info("Password saved successfully. UserId: " + passwordDto.getUserId());
			return true;
		} else {
			log.info("Failed to save password. UserId: " + passwordDto.getUserId());
			log.info("Reason: " + response.getMessage());
			return false;
		}
	}

}
