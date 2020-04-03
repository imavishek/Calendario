/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.configuration
 * @FileName: SwaggerConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:32:27 pm
 */

package com.calendario.oauth.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.calendario.global.common.microservice.constant.SwaggerInfo;

import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.calendario.oauth.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext())).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.POST, customizedResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, customizedResponseMessages())
				.globalResponseMessage(RequestMethod.GET, customizedResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, customizedResponseMessages());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("OAuth Service Rest Api", "OAuth Details Available", "V-1.0", "API Terms of Service URL",
				new Contact("Calendario", "https://github.com/imavishek/Calendario", "calendario.online.2020@gmail.com"),
				"API License", "API License URL", Collections.emptyList());
	}

	private List<ResponseMessage> customizedResponseMessages() {
		List<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder().code(0).message("Failed").build());
		responseMessages.add(new ResponseMessageBuilder().code(1).message("Success").build());
		responseMessages.add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
		responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());
		responseMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
		responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found").build());
		responseMessages.add(new ResponseMessageBuilder().code(405).message("Method Not Allowed").build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").build());
		responseMessages.add(new ResponseMessageBuilder().code(503).message("Service Unavailable").build());
		responseMessages.add(new ResponseMessageBuilder().code(1000).message("Oauth Server Error").build());

		return responseMessages;
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(SwaggerInfo.ACCESS_TOKEN_URL);

		SecurityScheme oauth = new OAuthBuilder().name(SwaggerInfo.SECURITY_SCHEME_NAME)
				.grantTypes(Arrays.asList(grantType)).scopes(Arrays.asList(scopes())).build();

		return oauth;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference(SwaggerInfo.SECURITY_SCHEME_NAME, scopes())))
				.forPaths(PathSelectors.any()).build();
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for write operations") };

		return scopes;
	}
}
