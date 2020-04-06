/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.entities
 * @FileName: Event.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 8:43:11 pm
 */

package com.calendario.user.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "event")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Event implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "event_id")
	private UUID eventId;

	@OneToOne
	@JoinColumn(name = "slot_id", referencedColumnName = "slot_id")
	private AvailableSlot slot;

	@OneToOne
	@JoinColumn(name = "participant_id", referencedColumnName = "user_id")
	private User participant;

	private String link;

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
