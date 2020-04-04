/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.properties
 * @FileName: UrlProperties.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 11:50:08 pm
 */

package com.calendario.user.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@ConfigurationProperties(prefix = "url")
@Component
@RequestScope
@Data
public class UrlProperties {

	private String oauth_server_save_password;
}
