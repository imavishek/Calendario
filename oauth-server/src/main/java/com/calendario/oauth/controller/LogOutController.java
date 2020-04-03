/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.controller
 * @FileName: LogOutController.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:13:08 pm
 */

package com.calendario.oauth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.global.common.microservice.constant.OAuthServerEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.oauth.properties.MessageProperties;
import com.calendario.oauth.service.LogOutService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@Api(tags = "Logout API", description = "Handle logout operation")
public class LogOutController {

	@Autowired
	private LogOutService logOutService;

	@Autowired
	private MessageProperties messageProperties;

	@DeleteMapping(value = OAuthServerEndpointUrl.REVOKE_ACCESS_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Revoke access token")
	public Response<Object> revokeToken(HttpServletRequest request) {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = logOutService.revokeToken(request);

		if (success) {
			status = Status.C_1;
			message = messageProperties.getAccess_token_revoke_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getAccess_token_revoke_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}
}
