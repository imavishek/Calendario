/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.serviceImpl
 * @FileName: AvailableSlotServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 6:58:30 pm
 */

package com.calendario.user.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.user.dto.AvailableSlotsDto;
import com.calendario.user.dto.SlotDto;
import com.calendario.user.dto.SlotResponseDto;
import com.calendario.user.dto.SlotTimeDto;
import com.calendario.user.entities.AvailableSlot;
import com.calendario.user.entities.User;
import com.calendario.user.repository.AvailableSlotRepository;
import com.calendario.user.service.AvailableSlotService;
import com.calendario.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class AvailableSlotServiceImpl implements AvailableSlotService {

	@Autowired
	private UserService userService;

	@Autowired
	private AvailableSlotRepository availableSlotRepository;

	@Override
	public Boolean createSlots(SlotDto slotDto) throws CalendarioNotFoundApiException {
		Boolean success = false;
		UUID userId = slotDto.getUserId();
		User user = userService.getUserByUserId(userId);

		// Checking the existence of User
		if (user == null) {
			log.info("User not exists: " + userId);
			throw new CalendarioNotFoundApiException("User not found. UserId: " + userId);
		}

		List<AvailableSlot> slots = new ArrayList<AvailableSlot>();
		for (SlotTimeDto slotTime : slotDto.getSlotTimes()) {
			AvailableSlot slot = new AvailableSlot();
			slot.setDate(slotDto.getDate());
			slot.setDuration(slotDto.getDuration());
			slot.setStartTime(slotTime.getStartTime());
			slot.setEndTime(slotTime.getEndTime());
			slot.setTopic(slotDto.getTopic());
			slot.setUser(user);

			slots.add(slot);
		}

		availableSlotRepository.saveAll(slots);

		success = true;

		return success;
	}

	@Override
	public AvailableSlot getAvailableSlotBySlotId(UUID slotId) {
		return availableSlotRepository.findById(slotId).orElse(null);
	}

	@Override
	public void deActivateSlot(UUID slotId) throws CalendarioNotFoundApiException {
		AvailableSlot slot = availableSlotRepository.findById(slotId)
				.orElseThrow(() -> new CalendarioNotFoundApiException("Slot not found. SlotId: " + slotId));
		slot.setStatus((byte) 0);

		availableSlotRepository.save(slot);
	}

	@Override
	public AvailableSlotsDto getAvailableSlots(UUID userId) {
		List<AvailableSlot> availableSlots = availableSlotRepository.findByUserUserIdAndStatus(userId, (byte) 1);

		if (availableSlots.isEmpty())
			return null;

		List<SlotResponseDto> slots = availableSlots.stream()
				.map(availableSlot -> new SlotResponseDto(availableSlot.getSlotId(), availableSlot.getDate(),
						availableSlot.getDuration(), availableSlot.getStartTime(), availableSlot.getEndTime(),
						availableSlot.getTopic()))
				.collect(Collectors.toList());

		AvailableSlotsDto availableSlotsDto = new AvailableSlotsDto();
		availableSlotsDto.setUserId(userId);
		availableSlotsDto.setName(availableSlots.get(0).getUser().getName());
		availableSlotsDto.setSlots(slots);

		return availableSlotsDto;
	}

}
