/**
 * @ProjectName: zuul-gateway
 * @PackageName: com.calendario.zuul.handler
 * @FileName: ZuulFallbackProvider.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 3:45:19 pm
 */

package com.calendario.zuul.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulFallbackProvider implements FallbackProvider {

	@Autowired
	private ObjectMapper objectMapper;

	/* provide a default fallback for all routes */
	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable throwable) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.toString();
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() throws IOException {
				HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
				String message = "Service is unavailable or down. Please try later.";

				Response<Object> errorResponse = ResponseUtil.getResponse(status.value(), "Service Unavailable",
						message, null);
				String response = objectMapper.writeValueAsString(errorResponse);

				log.error(response.toString());

				return new ByteArrayInputStream(response.getBytes("UTF-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}

}
