/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.producer
 * @FileName: RegistrationSuccessMailProducer.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 2:14:55 pm
 */

package com.calendario.user.producer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.constant.RabbitMQConstant;
import com.calendario.global.common.microservice.dto.MessageDto;
import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.user.entities.User;
import com.calendario.user.properties.RegistrationSuccessProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RefreshScope
public class RegistrationSuccessMailProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RegistrationSuccessProperties registrationSuccessProperties;

	public void sendMail(Object event) throws CalendarioBadRequestApiException {
		User user = (User) event;

		Map<String, String> contentDetails = new HashMap<String, String>();
		contentDetails.put("recipientName", user.getName());

		MessageDto messageDto = new MessageDto();
		messageDto.setForm(registrationSuccessProperties.getFrom());
		messageDto.setTo(user.getEmail());
		messageDto.setSubject(registrationSuccessProperties.getSubject());
		messageDto.setTemplateName(registrationSuccessProperties.getTemplateName());
		messageDto.setContentDetails(contentDetails);

		try {
			rabbitTemplate.convertAndSend(RabbitMQConstant.REGD_SUCCESSFUL_EXCHANGE,
					RabbitMQConstant.REGD_SUCCESSFUL_ROUTING, objectMapper.writeValueAsString(messageDto));
			log.info("Message (Registration success Mail) Published Successfully");
			log.info("Sending event message to Exchange '" + RabbitMQConstant.REGD_SUCCESSFUL_EXCHANGE + "' with Json: "
					+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageDto));
		} catch (AmqpException | JsonProcessingException e) {
			throw new CalendarioBadRequestApiException("RegistrationSuccessMailProducer parsing error");
		}

	}
}