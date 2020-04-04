/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.entities
 * @FileName: AvailableSlot.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 8:36:50 pm
 */

package com.calendario.user.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "available_slot")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AvailableSlot implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "slot_id")
	private UUID slotId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private Integer duration;

	@Column(name = "start_time")
	@JsonFormat(pattern = "HH:MM")
	private LocalTime startTime;

	@Column(name = "end_time")
	@JsonFormat(pattern = "HH:MM")
	private LocalTime endTime;

	private String reason;

	@Column(name = "status", insertable = false)
	private Byte status;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@Column(name = "created_date", updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime recordCreated;

	@Column(name = "updated_date", insertable = false)
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordUpdated;
}
