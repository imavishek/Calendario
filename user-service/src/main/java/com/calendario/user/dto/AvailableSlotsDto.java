/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.dto
 * @FileName: AvailableSlotsDto.java
 * @Author: Avishek Das
 * @CreatedDate: 06-04-2020
 * @Modified_By avishekdas @Last_On 06-Apr-2020 8:30:42 pm
 */

package com.calendario.user.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Available slot response dto")
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotsDto {

	private UUID userId;

	private String name;

	private List<SlotResponseDto> slots;
}
