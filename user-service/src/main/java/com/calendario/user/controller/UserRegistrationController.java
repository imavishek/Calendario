/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.controller
 * @FileName: UserRegistrationController.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:30:23 pm
 */

package com.calendario.user.controller;

import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.global.common.microservice.constant.UserServiceEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.global.common.microservice.exceptions.CalendarioInvalidTokenException;
import com.calendario.global.common.microservice.exceptions.CalendarioUserEmailExistsException;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.dto.ActiveProfileDto;
import com.calendario.user.dto.UserRegisterDto;
import com.calendario.user.properties.MessageProperties;
import com.calendario.user.properties.TimeProperties;
import com.calendario.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@Api(tags = "User Registration API", description = "Handle user registration operation")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageProperties messageProperties;

	@Autowired
	private TimeProperties timeProperties;

	/**
	 * Method is used as a endpoint for user registration.
	 * 
	 * @param userRegisterDto : Object containing the value of registration field.
	 * @return Response : Object containing the response status, message and data.
	 * @throws CalendarioUserEmailExistsException
	 * @throws CalendarioBadRequestApiException
	 */
	@PostMapping(value = UserServiceEndpointUrl.REGISTER_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Register user")
	public Response<Object> register(@RequestBody UserRegisterDto userRegisterDto)
			throws CalendarioUserEmailExistsException, CalendarioBadRequestApiException {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = userService.register(userRegisterDto);

		if (success) {
			status = Status.C_1;
			message = messageProperties.getRegistration_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getRegistration_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}

	/**
	 * Method is used as a endpoint for validate user token.
	 * 
	 * @param token : Token used to validate the user
	 * @return Response : Object containing the response status, message and data.
	 */
	@GetMapping(value = UserServiceEndpointUrl.VALIDATE_REGISTER_USER_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Validate user token")
	public Response<Object> validateUserToken(@PathVariable UUID token) {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = userService.isValidToken(token, timeProperties.getExpiration_activeaccount());

		if (success) {
			status = Status.C_1;
			message = messageProperties.getValid_token() + " Token: " + token;
		} else {
			status = Status.C_0;
			message = messageProperties.getInvalid_token() + " Token: " + token;
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}

	/**
	 * Method is used as a endpoint to confirm user registration.
	 * 
	 * @param activeProfileDto : Object containing the token and password.
	 * @return Response : Object containing the response status, message and data.
	 * @throws CalendarioInvalidTokenException
	 * @throws URISyntaxException
	 * @throws CalendarioBadRequestApiException
	 */
	@PostMapping(value = UserServiceEndpointUrl.REGISTRATION_CONFIRM, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Registration Confirm")
	public Response<Object> activeProfile(@RequestBody ActiveProfileDto activeProfileDto)
			throws CalendarioInvalidTokenException, URISyntaxException, CalendarioBadRequestApiException {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = userService.activeProfile(activeProfileDto);

		if (success) {
			status = Status.C_1;
			message = messageProperties.getRegistration_confirm_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getRegistration_confirm_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}
}
