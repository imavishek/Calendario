/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: PasswordDto.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 11:38:49 pm
 */

package com.calendario.user.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Password operation dto")
public class PasswordDto {

	private UUID userId;

	private String username;

	private String password;
}
