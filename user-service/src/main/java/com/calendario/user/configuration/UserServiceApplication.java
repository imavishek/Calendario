/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.configuration
 * @FileName: UserServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 7:04:17 pm
 */

package com.calendario.user.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.calendario.global.common.microservice.*", "com.calendario.user.*" })
@EnableDiscoveryClient
@EntityScan(basePackages = { "com.calendario.user.entities" })
@EnableJpaRepositories(basePackages = { "com.calendario.user.repository" })
@EnableJpaAuditing
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
