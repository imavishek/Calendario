/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.serviceImpl
 * @FileName: LogOutServiceImplTest.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 1:08:39 am
 */

package com.calendario.oauth.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class LogOutServiceImplTest {

	@Mock
	private DefaultTokenServices tokenServices;

	@InjectMocks
	private LogOutServiceImpl logOutService;

	@Test
	public void revokeToken() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer aad-34443-ccccfefe");

		// For Success case
		when(tokenServices.revokeToken(anyString())).thenReturn(true);

		Boolean success = logOutService.revokeToken(request);

		assertEquals(true, success);

		// For Failure case
		when(tokenServices.revokeToken(anyString())).thenReturn(false);

		success = logOutService.revokeToken(request);

		assertEquals(false, success);

		// For Failure case
		request.addHeader("Authorization", "aad-34443-ccccfefe");

		when(tokenServices.revokeToken(anyString())).thenReturn(false);

		success = logOutService.revokeToken(request);

		assertEquals(false, success);

	}
}
