/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.exception
 * @FileName: CustomOauthException.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:37:53 pm
 */

package com.calendario.oauth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.oauth.util.CustomOauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

	public CustomOauthException(String msg) {
		super(msg);
	}

	public String getOAuth2ErrorCode() {
		return Status.C_1000.getMessage();
	}

	public int getHttpErrorCode() {
		return Status.C_1000.getCode();
	}
}
