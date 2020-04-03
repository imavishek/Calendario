/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.controller
 * @FileName: LogOutControllerTest.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 1:02:06 am
 */

package com.calendario.oauth.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.calendario.global.common.microservice.constant.OAuthServerEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.oauth.properties.MessageProperties;
import com.calendario.oauth.service.LogOutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
public class LogOutControllerTest {

	@Mock
	private LogOutService logOutService;

	@Mock
	private MessageProperties messageProperties;

	@InjectMocks
	private LogOutController logOutController;

	private MockMvc mockMvc;

	private static ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		mockMvc = MockMvcBuilders.standaloneSetup(logOutController).build();
	}

	@Test
	public void revokeTokenTest() throws Exception {

		String url = OAuthServerEndpointUrl.REVOKE_ACCESS_TOKEN;

		// For Success case
		when(logOutService.revokeToken(any(HttpServletRequest.class))).thenReturn(true);

		MvcResult result = mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContent, Response.class);

		assertEquals(Status.C_1.getCode(), response.getCode(), "Expected 1 but getting :" + response.getCode());

		// For Failure case
		when(logOutService.revokeToken(any(HttpServletRequest.class))).thenReturn(false);

		result = mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
		resultContent = result.getResponse().getContentAsString();
		response = objectMapper.readValue(resultContent, Response.class);

		assertEquals(Status.C_0.getCode(), response.getCode(), "Expected 0 but getting :" + response.getCode());
	}
}
