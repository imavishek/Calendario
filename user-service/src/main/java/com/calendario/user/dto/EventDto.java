/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: EventDto.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 9:38:28 pm
 */

package com.calendario.user.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Event operation dto")
public class EventDto {

	private UUID slotId;

	private UUID participantId;
}
