/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.dto
 * @FileName: PasswordDto.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:45:46 pm
 */

package com.calendario.oauth.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Password operation dto")
public class PasswordDto {

	private String userId;

	private String username;

	private String password;
}
