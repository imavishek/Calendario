/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.configuration
 * @FileName: OauthServerApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:10:07 pm
 */

package com.calendario.oauth.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = { "com.calendario.global.common.microservice.*", "com.calendario.oauth.*" })
@EntityScan(basePackages = { "com.calendario.oauth.entities" })
@EnableJpaRepositories(basePackages = { "com.calendario.oauth.repository" })
@EnableJpaAuditing
@EnableDiscoveryClient
public class OauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthServerApplication.class, args);
	}
}
