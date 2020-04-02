/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.dto
 * @FileName: Response.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 3:43:04 pm
 */

package com.calendario.global.common.microservice.dto;

import java.time.LocalDateTime;

import com.calendario.global.common.microservice.constant.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Common Response")
public class Response<T> {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFormat.US_LOCAL_DATE_TIME)
	private LocalDateTime timestamp;

	private Integer code;

	private String status;

	private String message;

	private T data;

}
