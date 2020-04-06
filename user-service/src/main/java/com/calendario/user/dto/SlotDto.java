/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: SlotDto.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 6:43:36 pm
 */

package com.calendario.user.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Available slot operation dto")
public class SlotDto {

	private UUID userId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private Integer duration;

	private List<SlotTimeDto> slotTimes;

	private String topic;
}
