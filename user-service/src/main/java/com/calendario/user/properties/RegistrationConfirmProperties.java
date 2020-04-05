/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.properties
 * @FileName: RegistrationConfirmProperties.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:26:25 pm
 */

package com.calendario.user.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@ConfigurationProperties(prefix = "mail.registration-confirm")
@Component
@RequestScope
@Data
public class RegistrationConfirmProperties {

	private String from;

	private String subject;

	private String templateName;

	private String url;
}
