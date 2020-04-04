/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: UserRegisterDto.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:29:28 pm
 */

package com.calendario.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "User register operation dto")
public class UserRegisterDto {

	private String name;

	private String email;
}
