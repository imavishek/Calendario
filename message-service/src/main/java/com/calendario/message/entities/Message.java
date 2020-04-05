/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.entities
 * @FileName: Message.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:43:01 pm
 */

package com.calendario.message.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Message implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "message_id")
	private UUID messageId;

	private String content;

	@Column(name = "status", insertable = false)
	private Byte status;

	@Column(name = "created_date", updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime recordCreated;

	@Column(name = "updated_date", insertable = false)
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordUpdated;
}
