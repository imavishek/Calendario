/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.util
 * @FileName: CustomTokenEnhancer.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:35:00 pm
 */

package com.calendario.oauth.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.calendario.oauth.dto.LoginDetails;
import com.calendario.oauth.entities.User;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		LoginDetails loginDetails = (LoginDetails) authentication.getPrincipal();

		User user = loginDetails.getUser();

		Map<String, Object> additionalInfo = new HashMap<String, Object>(accessToken.getAdditionalInformation());

		additionalInfo.put("username", user.getUsername());
		additionalInfo.put("userId", user.getUserId());

		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(additionalInfo);

		return customAccessToken;
	}
}
