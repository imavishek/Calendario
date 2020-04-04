/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.util
 * @FileName: CustomAccessTokenConverter.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 7:33:01 pm
 */

package com.calendario.user.util;

import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
		OAuth2Authentication authentication = super.extractAuthentication(claims);
		authentication.setDetails(claims);

		return authentication;
	}
}
