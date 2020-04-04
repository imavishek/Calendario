/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.entities
 * @FileName: User.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 8:27:07 pm
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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "user_id")
	private UUID userId;

	private String name;

	private String email;

	private UUID token;

	@Column(name = "token_generate_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tokenGeneratedDate;

	@Column(name = "active", insertable = false)
	private Byte active;

	@Column(name = "created_date", updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime recordCreated;

	@Column(name = "updated_date", insertable = false)
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordUpdated;
}
