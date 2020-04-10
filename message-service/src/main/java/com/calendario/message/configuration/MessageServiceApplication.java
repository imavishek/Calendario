/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.configuration
 * @FileName: MessageServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 11:38:11 am
 */

package com.calendario.message.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.calendario.global.common.microservice.*", "com.calendario.message.*" })
@EnableDiscoveryClient
@EntityScan(basePackages = { "com.calendario.message.entities" })
@EnableJpaRepositories(basePackages = { "com.calendario.message.repository" })
@EnableJpaAuditing
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

}
