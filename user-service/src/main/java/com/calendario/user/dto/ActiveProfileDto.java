/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: ActiveProfileDto.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:42:53 pm
 */

package com.calendario.user.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Active profile operation dto")
public class ActiveProfileDto {

	private UUID token;

	private String password;

}
