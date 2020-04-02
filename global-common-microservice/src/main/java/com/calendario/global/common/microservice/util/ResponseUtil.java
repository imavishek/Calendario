/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.util
 * @FileName: ResponseUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 4:37:11 pm
 */

package com.calendario.global.common.microservice.util;

import java.time.LocalDateTime;

import com.calendario.global.common.microservice.dto.Response;

public class ResponseUtil {

	public static Response<Object> getResponse(Integer code, String status, String message, Object data) {
		Response<Object> response = new Response<Object>();
		response.setTimestamp(LocalDateTime.now());
		response.setCode(code);
		response.setStatus(status);
		response.setMessage(message);
		if (data != null) {
			response.setData(data);
		}

		return response;

	}
}
