/**
 * @ProjectName: eureka-server
 * @PackageName: com.calendario.eureka.configuration
 * @FileName: SpringSecurityConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 01-04-2020
 * @Modified_By avishekdas @Last_On 01-Apr-2020 11:32:29 pm
 */

package com.calendario.eureka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		super.configure(http);
	}
}
