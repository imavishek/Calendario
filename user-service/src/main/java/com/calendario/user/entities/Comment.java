/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.entities
 * @FileName: Comment.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2020
 */

package com.calendario.user.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Comment implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "comment_id")
	private UUID commentId;

	@ManyToOne
	@JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
	@JsonIgnore
	private Post post;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "parent_comment_id", referencedColumnName = "comment_id", nullable = true)
	// @JsonBackReference
	@JsonIgnore
	private Comment parentComment;

	@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
	// @JsonManagedReference
	@JsonIgnore
	private Set<Comment> childComments;

	@Column(name = "content")
	private String content;

	@Column(name = "commented_date", updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime commentedDate;

	@Column(name = "updated_date", insertable = false)
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedDate;
}
