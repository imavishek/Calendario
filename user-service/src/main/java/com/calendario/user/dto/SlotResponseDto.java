/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: SlotResponseDto.java
 * @Author: Avishek Das
 * @CreatedDate: 06-04-2020
 * @Modified_By avishekdas @Last_On 06-Apr-2020 8:28:09 pm
 */

package com.calendario.user.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Slot response dto")
@AllArgsConstructor
@NoArgsConstructor
public class SlotResponseDto {

	private UUID slotId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private Integer duration;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime startTime;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime endTime;

	private String topic;
}
