/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.service
 * @FileName: UserService.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:32:57 pm
 */

package com.calendario.user.service;

import java.net.URISyntaxException;
import java.time.Duration;
import java.util.UUID;

import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.global.common.microservice.exceptions.CalendarioInvalidTokenException;
import com.calendario.global.common.microservice.exceptions.CalendarioUserEmailExistsException;
import com.calendario.user.dto.ActiveProfileDto;
import com.calendario.user.dto.UserRegisterDto;
import com.calendario.user.entities.User;

public interface UserService {

	Boolean register(UserRegisterDto userRegisterDto)
			throws CalendarioUserEmailExistsException, CalendarioBadRequestApiException;

	Boolean isValidToken(UUID token, Duration expiration);

	Boolean activeProfile(ActiveProfileDto activeProfileDto)
			throws CalendarioInvalidTokenException, URISyntaxException, CalendarioBadRequestApiException;

	User getUserByEmail(String email);
}
