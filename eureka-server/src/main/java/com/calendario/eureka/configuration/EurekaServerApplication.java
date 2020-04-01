/**
 * @ProjectName: eureka-server
 * @PackageName: com.calendario.eureka.configuration
 * @FileName: EurekaServerApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 01-04-2020
 * @Modified_By avishekdas @Last_On 01-Apr-2020 11:29:14 pm
 */

package com.calendario.eureka.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
