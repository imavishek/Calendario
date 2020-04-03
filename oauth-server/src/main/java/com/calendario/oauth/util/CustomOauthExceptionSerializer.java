/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.util
 * @FileName: CustomOauthExceptionSerializer.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:42:01 pm
 */

package com.calendario.oauth.util;

import java.io.IOException;

import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.oauth.exception.CustomOauthException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

	public CustomOauthExceptionSerializer() {
		super(CustomOauthException.class);
	}

	@Override
	public void serialize(CustomOauthException value, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException {

		Response<Object> response = ResponseUtil.getResponse(value.getHttpErrorCode(), value.getOAuth2ErrorCode(),
				value.getMessage(), null);

		log.error(response.toString());

		jsonGenerator.writeObject(response);
	}
}
