/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.configuration
 * @FileName: ResourceServerConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 7:06:04 pm
 */

package com.calendario.user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.calendario.global.common.microservice.constant.UserServiceEndpointUrl;

@Configuration
@EnableOAuth2Sso
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private ResourceServerTokenServices resourceServerTokenServices;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
				.antMatchers(UserServiceEndpointUrl.REGISTER_USER).permitAll()
				.antMatchers(UserServiceEndpointUrl.REGISTRATION_CONFIRM).permitAll()
				.antMatchers(UserServiceEndpointUrl.VALIDATE_REGISTER_USER_TOKEN).permitAll()
				.antMatchers("/actuator/**").permitAll().antMatchers("/h2-console/**").permitAll()
				.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/csrf", "/")
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
				.tokenServices(resourceServerTokenServices);
	}

}
