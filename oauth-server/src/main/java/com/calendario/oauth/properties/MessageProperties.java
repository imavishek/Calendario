/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.properties
 * @FileName: MessageProperties.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:05:34 pm
 */

package com.calendario.oauth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@ConfigurationProperties(prefix = "message")
@Component
@RequestScope
@Data
public class MessageProperties {

	private String password_saved_success;

	private String password_saved_failed;

	private String bad_credential;

	private String forbidden;

	private String access_token_revoke_success;

	private String access_token_revoke_failed;
}
