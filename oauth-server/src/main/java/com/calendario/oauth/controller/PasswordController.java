/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.controller
 * @FileName: PasswordController.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 10:07:36 pm
 */

package com.calendario.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.global.common.microservice.constant.OAuthServerEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.oauth.dto.PasswordDto;
import com.calendario.oauth.properties.MessageProperties;
import com.calendario.oauth.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@Api(tags = "Password API", description = "Handle password operation")
public class PasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageProperties messageProperties;

	@PostMapping(value = OAuthServerEndpointUrl.SAVE_PASSWORD, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Save password")
	public Response<Object> savePassword(@RequestBody PasswordDto passwordDto) {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = userService.savePassword(passwordDto);

		if (success) {
			status = Status.C_1;
			message = messageProperties.getPassword_saved_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getPassword_saved_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}

}
