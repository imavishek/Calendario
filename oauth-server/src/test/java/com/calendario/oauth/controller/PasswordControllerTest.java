/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.controller
 * @FileName: PasswordControllerTest.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 1:03:13 am
 */

package com.calendario.oauth.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.calendario.oauth.dto.PasswordDto;
import com.calendario.oauth.properties.MessageProperties;
import com.calendario.oauth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
public class PasswordControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private MessageProperties messageProperties;

	@InjectMocks
	private PasswordController passwordController;

	private MockMvc mockMvc;

	private static ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		mockMvc = MockMvcBuilders.standaloneSetup(passwordController).build();
	}

	@Test
	public void savePasswordTest() throws Exception {

		String url = OAuthServerEndpointUrl.SAVE_PASSWORD;

		PasswordDto passwordDto = new PasswordDto();
		passwordDto.setUserId("12fef224-7665-41bd-bc26-cb5dc71af82d");
		passwordDto.setUsername("avishek.akd@gmail.com");
		passwordDto.setPassword("BYmalZGHvsPm/dUXipM0FkTcfq8oUICt2tvX+HBZjO0=");

		// For Success case
		when(userService.savePassword(passwordDto)).thenReturn(true);

		String payload = objectMapper.writeValueAsString(passwordDto);
		MvcResult result = mockMvc.perform(post(url).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContent, Response.class);

		assertEquals(Status.C_1.getCode(), response.getCode(), "Expected 1 but getting :" + response.getCode());

		// For Failure case
		when(userService.savePassword(passwordDto)).thenReturn(false);

		result = mockMvc.perform(post(url).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		resultContent = result.getResponse().getContentAsString();
		response = objectMapper.readValue(resultContent, Response.class);

		assertEquals(Status.C_0.getCode(), response.getCode(), "Expected 0 but getting :" + response.getCode());
	}
}
