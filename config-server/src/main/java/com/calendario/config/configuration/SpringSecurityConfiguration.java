/**
 * @ProjectName: config-server
 * @PackageName: com.calendario.config.configuration
 * @FileName: SpringSecurityConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 1:20:15 pm
 */

package com.calendario.config.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/monitor*").permitAll().and().csrf().disable();
		super.configure(http);
	}
}
