/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: SlotTimeDto.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 7:20:28 pm
 */

package com.calendario.user.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Available slottime operation dto")
public class SlotTimeDto {

	@JsonFormat(pattern = "HH:MM")
	private LocalTime startTime;

	@JsonFormat(pattern = "HH:MM")
	private LocalTime endTime;
}
