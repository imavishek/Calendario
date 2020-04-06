/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: EventDetailsDto.java
 * @Author: Avishek Das
 * @CreatedDate: 06-04-2020
 * @Modified_By avishekdas @Last_On 06-Apr-2020 9:38:56 pm
 */

package com.calendario.user.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Event response dto")
public class EventDetailsDto {

	private UUID eventId;

	private String topic;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private Integer duration;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime startTime;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime endTime;

	private String hostName;

	private String participantName;

	private String link;
}
