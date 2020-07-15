/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.properties
 * @FileName: MessageProperties.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:17:08 pm
 */

package com.calendario.user.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@ConfigurationProperties(prefix = "message")
@Component
@RequestScope
@Data
public class MessageProperties {

	private String registration_success;

	private String registration_failed;

	private String registration_confirm_success;

	private String registration_confirm_failed;

	private String email_exist;

	private String invalid_token;

	private String valid_token;

	private String invalid_access_token;

	private String access_denied;

	private String slot_create_success;

	private String slot_create_failed;

	private String event_create_success;

	private String event_create_failed;

	private String post_create_success;

	private String post_create_failed;

	private String comment_add_success;

	private String comment_add_failed;
}
