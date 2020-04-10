/**
 * @ProjectName: eureka-server
 * @PackageName: com.calendario.eureka.configuration
 * @FileName: WebConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 10-04-2020
 * @Modified_By avishekdas @Last_On 10-Apr-2020 1:37:46 pm
 */

package com.calendario.eureka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/eureka/**").addResourceLocations("/eureka/", "classpath:/static/eureka/");
	}
}
