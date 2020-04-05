/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.serviceImpl
 * @FileName: MessageService.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:42:15 pm
 */

package com.calendario.message.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendario.global.common.microservice.dto.MessageDto;
import com.calendario.message.entities.Message;
import com.calendario.message.repository.MessageRepository;
import com.calendario.message.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void saveMessage(MessageDto messageDto) throws JsonProcessingException {
		Message message = new Message();
		message.setContent(objectMapper.writeValueAsString(messageDto));

		messageRepository.save(message);
	}

}
