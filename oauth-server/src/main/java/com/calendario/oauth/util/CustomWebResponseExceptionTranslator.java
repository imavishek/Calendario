/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.util
 * @FileName: CustomWebResponseExceptionTranslator.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:36:00 pm
 */

package com.calendario.oauth.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

import com.calendario.oauth.exception.CustomOauthException;

public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {
		ResponseEntity<OAuth2Exception> responseEntity = super.translate(exception);
		OAuth2Exception oAuth2Exception = responseEntity.getBody();

		return ResponseEntity.status(HttpStatus.OK.value())
				.body(new CustomOauthException(oAuth2Exception.getMessage()));
	}

}
