/**
 * @ProjectName: zuul-gateway
 * @PackageName: com.calendario.zuul.configuration
 * @FileName: ZuulGatewayApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 3:15:08 pm
 */

package com.calendario.zuul.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class }, scanBasePackages = {
				"com.calendario.global.common.microservice.*", "com.calendario.zuul.*" })
@EnableZuulProxy
@EnableEurekaClient
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}

}
