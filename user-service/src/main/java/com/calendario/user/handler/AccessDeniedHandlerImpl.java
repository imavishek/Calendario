/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.handler
 * @FileName: AccessDeniedHandlerImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:16:33 pm
 */

package com.calendario.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.properties.MessageProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Autowired
	private MessageProperties messageProperties;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse res, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		OAuth2AccessToken accessToken = tokenServices.readAccessToken(details.getTokenValue());

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = messageProperties.getAccess_denied();

		Response<Object> response = ResponseUtil.getResponse(status.value(), "Anauthorized", message, null);

		log.error(response.toString());
		log.error(accessToken.getAdditionalInformation().toString());

		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		res.getOutputStream().println(objectMapper.writeValueAsString(response));

	}
}
