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

import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.handler.ApiExceptionHandler;
import com.calendario.global.common.microservice.util.ResponseUtil;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class }, scanBasePackages = {
				"com.calendario.zuul.*" }, scanBasePackageClasses = { ApiExceptionHandler.class, Status.class,
						Response.class, ResponseUtil.class })
@EnableZuulProxy
@EnableEurekaClient
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}

}
