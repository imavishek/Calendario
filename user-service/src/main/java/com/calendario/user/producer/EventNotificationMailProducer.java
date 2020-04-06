/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.producer
 * @FileName: EventNotificationMailProducer.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 9:58:43 pm
 */

package com.calendario.user.producer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.constant.RabbitMQConstant;
import com.calendario.global.common.microservice.dto.MessageDto;
import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.user.entities.AvailableSlot;
import com.calendario.user.entities.User;
import com.calendario.user.properties.EventNotificationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RefreshScope
public class EventNotificationMailProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EventNotificationProperties eventNotificationProperties;

	public void sendMail(User host, User participant, AvailableSlot slot, String link, UUID uuid)
			throws CalendarioBadRequestApiException {

		Map<String, String> contentDetails = new HashMap<String, String>();
		contentDetails.put("slotDate", slot.getDate().toString());
		contentDetails.put("startTime", slot.getStartTime().toString());
		contentDetails.put("endTime", slot.getEndTime().toString());
		contentDetails.put("topic", slot.getTopic());
		contentDetails.put("hostName", host.getName());
		contentDetails.put("participantName", participant.getName());
		contentDetails.put("link", link);
		contentDetails.put("eventId", uuid.toString());
		contentDetails.put("attachCalender", "true");

		MessageDto messageDto = new MessageDto();
		messageDto.setForm(eventNotificationProperties.getFrom());
		messageDto.setTo(host.getEmail());
		messageDto.setCc(participant.getEmail());
		messageDto.setSubject(eventNotificationProperties.getSubject());
		messageDto.setTemplateName(eventNotificationProperties.getTemplateName());
		messageDto.setContentDetails(contentDetails);

		try {
			rabbitTemplate.convertAndSend(RabbitMQConstant.EVENT_NOTIFICATION_EXCHANGE,
					RabbitMQConstant.EVENT_NOTIFICATION_ROUTING, objectMapper.writeValueAsString(messageDto));
			log.info("Message (Event Notification Mail) Published Successfully");
			log.info("Sending event message to Exchange '" + RabbitMQConstant.REGD_EXCHANGE + "' with Json: "
					+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageDto));
		} catch (AmqpException | JsonProcessingException e) {
			throw new CalendarioBadRequestApiException("EventNotificationMailProducer parsing error");
		}

	}
}
