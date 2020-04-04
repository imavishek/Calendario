/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.properties
 * @FileName: TimeProperties.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:33:27 pm
 */

package com.calendario.user.properties;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@ConfigurationProperties(prefix = "time")
@Component
@RequestScope
@Data
public class TimeProperties {

	private Duration expiration_activeaccount = Duration.ofMillis(1000);

	private Duration expiration_resetpassword = Duration.ofMillis(1000);

}
