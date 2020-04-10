/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.service
 * @FileName: AvailableSlotService.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 6:53:18 pm
 */

package com.calendario.user.service;

import java.util.UUID;

import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.user.dto.AvailableSlotsDto;
import com.calendario.user.dto.SlotDto;
import com.calendario.user.entities.AvailableSlot;

public interface AvailableSlotService {

	Boolean createSlots(SlotDto slotDto) throws CalendarioNotFoundApiException;

	AvailableSlot getAvailableSlotBySlotId(UUID slotId) throws CalendarioNotFoundApiException;

	void deActivateSlot(UUID slotId) throws CalendarioNotFoundApiException;

	AvailableSlotsDto getAvailableSlots(UUID userId);
}
