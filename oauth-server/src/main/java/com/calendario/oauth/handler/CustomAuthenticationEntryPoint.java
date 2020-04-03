/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.handler
 * @FileName: CustomAuthenticationEntryPoint.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:58:28 pm
 */

package com.calendario.oauth.handler;

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
import com.calendario.oauth.properties.MessageProperties;
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
		String message = messageProperties.getBad_credential();

		Response<Object> response = ResponseUtil.getResponse(status.value(), "Unauthorized", message, null);

		log.error(response.toString());

		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(HttpStatus.OK.value());
		res.getOutputStream().println(objectMapper.writeValueAsString(response));

	}

}
