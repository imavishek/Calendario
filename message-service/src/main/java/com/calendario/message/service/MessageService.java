/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.service
 * @FileName: MessageService.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:40:48 pm
 */

package com.calendario.message.service;

import com.calendario.global.common.microservice.dto.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageService {

	void saveMessage(MessageDto messageDto) throws JsonProcessingException;
}
