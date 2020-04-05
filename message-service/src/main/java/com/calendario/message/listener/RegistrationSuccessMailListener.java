/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.listener
 * @FileName: RegistrationSuccessMailListener.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 2:21:37 pm
 */

package com.calendario.message.listener;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.constant.RabbitMQConstant;
import com.calendario.global.common.microservice.dto.MessageDto;
import com.calendario.message.service.MessageService;
import com.calendario.message.utils.MessageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistrationSuccessMailListener {

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = RabbitMQConstant.REGD_SUCCESSFUL_QUEUE)
	public void sendMail(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {

		MessageDto messageDto = objectMapper.readValue(message, MessageDto.class);

		log.info("Message (Registration Success Mail) Consumed Successfully. Message: " + messageDto.toString());
		log.info("Mail Sending... Subject: " + messageDto.getSubject());

		Boolean success = messageUtil.sendEmail(messageDto);

		messageService.saveMessage(messageDto);

		if (success) {
			channel.basicAck(tag, false);
			log.info("Registration Success Mail Acknowledged");
		} else {
			channel.basicReject(tag, true);
			log.info("Do Not Discard (Requeue) Registration Mail");
		}
	}
}
