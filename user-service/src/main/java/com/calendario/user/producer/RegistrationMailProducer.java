/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.producer
 * @FileName: RegistrationMailProducer.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:05:52 pm
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
import com.calendario.user.properties.RegistrationConfirmProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RefreshScope
public class RegistrationMailProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RegistrationConfirmProperties registrationConfirmProperties;

	public void sendMail(Object event) throws CalendarioBadRequestApiException {
		User user = (User) event;

		Map<String, String> contentDetails = new HashMap<String, String>();
		contentDetails.put("recipientName", user.getName());
		contentDetails.put("url", registrationConfirmProperties.getUrl() + user.getToken());

		MessageDto messageDto = new MessageDto();
		messageDto.setForm(registrationConfirmProperties.getFrom());
		messageDto.setTo(user.getEmail());
		messageDto.setSubject(registrationConfirmProperties.getSubject());
		messageDto.setTemplateName(registrationConfirmProperties.getTemplateName());
		messageDto.setContentDetails(contentDetails);

		try {
			rabbitTemplate.convertAndSend(RabbitMQConstant.REGD_EXCHANGE, RabbitMQConstant.REGD_ROUTING,
					objectMapper.writeValueAsString(messageDto));
			log.info("Message (Registration Mail) Published Successfully");
			log.info("Sending event message to Exchange '" + RabbitMQConstant.REGD_EXCHANGE + "' with Json: "
					+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageDto));
		} catch (AmqpException | JsonProcessingException e) {
			throw new CalendarioBadRequestApiException("RegistrationMailProducer parsing error");
		}

	}
}
