/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.configuration
 * @FileName: SpringConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 7:28:11 pm
 */

package com.calendario.user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

import com.calendario.global.common.microservice.util.DateTime;
import com.calendario.user.util.CustomAccessTokenConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class SpringConfiguration {

	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;

	@Autowired
	private ResourceServerProperties resourceServerProperties;

	@Bean
	public DateTime dateTime() {
		return new DateTime();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper;
	}

	@Bean
	@Primary
	public ResourceServerTokenServices resourceServerTokenServices() {
		final RemoteTokenServices remoteTokenService = new RemoteTokenServices();

		remoteTokenService.setClientId(resourceServerProperties.getClientId());
		remoteTokenService.setClientSecret(resourceServerProperties.getClientSecret());
		remoteTokenService.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
		remoteTokenService.setAccessTokenConverter(customAccessTokenConverter);
		remoteTokenService.setRestTemplate(restTemplate());

		return remoteTokenService;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
