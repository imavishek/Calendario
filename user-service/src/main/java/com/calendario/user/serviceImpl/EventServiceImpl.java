/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.serviceImpl
 * @FileName: EventServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 9:43:49 pm
 */

package com.calendario.user.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.user.dto.EventDetailsDto;
import com.calendario.user.dto.EventDto;
import com.calendario.user.entities.AvailableSlot;
import com.calendario.user.entities.Event;
import com.calendario.user.entities.User;
import com.calendario.user.producer.EventNotificationMailProducer;
import com.calendario.user.repository.EventRepository;
import com.calendario.user.service.AvailableSlotService;
import com.calendario.user.service.EventService;
import com.calendario.user.service.UserService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AvailableSlotService availableSlotService;

	@Autowired
	private EventNotificationMailProducer eventNotificationMailProducer;

	@Override
	public Boolean createEvent(EventDto eventDto)
			throws CalendarioNotFoundApiException, CalendarioBadRequestApiException {
		Boolean success = false;

		AvailableSlot slot = availableSlotService.getAvailableSlotBySlotId(eventDto.getSlotId());
		User host = slot.getUser();
		User participant = userService.getUserByUserId(eventDto.getParticipantId());
		String link = "https://zoom.us/j/" + UUID.randomUUID();

		if (host == null || participant == null || slot == null) {
			throw new CalendarioNotFoundApiException("Not found Host/Participant/Slot");
		}

		Event event = new Event();
		event.setParticipant(participant);
		event.setSlot(slot);
		event.setLink(link);

		event = eventRepository.save(event);
		availableSlotService.deActivateSlot(slot.getSlotId());

		// Send mail both to host & participant
		eventNotificationMailProducer.sendMail(host, participant, slot, link, event.getEventId());

		success = true;

		return success;
	}

	@Override
	public EventDetailsDto getEventDetailsByEventId(UUID eventId) throws CalendarioNotFoundApiException {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new CalendarioNotFoundApiException("Event not found. EventId: " + eventId));

		AvailableSlot slot = event.getSlot();

		EventDetailsDto eventDetails = new EventDetailsDto();
		eventDetails.setEventId(event.getEventId());
		eventDetails.setTopic(slot.getTopic());
		eventDetails.setDate(slot.getDate());
		eventDetails.setDuration(slot.getDuration());
		eventDetails.setStartTime(slot.getStartTime());
		eventDetails.setEndTime(slot.getEndTime());
		eventDetails.setHostName(event.getSlot().getUser().getName());
		eventDetails.setParticipantName(event.getParticipant().getName());
		eventDetails.setLink(event.getLink());

		return eventDetails;
	}

}
