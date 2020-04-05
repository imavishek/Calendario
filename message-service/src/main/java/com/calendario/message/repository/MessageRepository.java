/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.repository
 * @FileName: MessageRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:47:08 pm
 */

package com.calendario.message.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendario.message.entities.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}
