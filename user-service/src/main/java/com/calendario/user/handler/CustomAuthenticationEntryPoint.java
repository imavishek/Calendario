/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.handler
 * @FileName: CustomAuthenticationEntryPoint.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:22:03 pm
 */

package com.calendario.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.properties.MessageProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private MessageProperties messageProperties;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse res, AuthenticationException authException)
			throws IOException, ServletException {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = messageProperties.getInvalid_access_token();

		Response<Object> response = ResponseUtil.getResponse(status.value(), "Unauthorized", message, null);

		log.error(response.toString());

		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		res.getOutputStream().println(objectMapper.writeValueAsString(response));

	}

}
