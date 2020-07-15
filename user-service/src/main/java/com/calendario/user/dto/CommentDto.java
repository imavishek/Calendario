/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.dto
 * @FileName: CommentDto.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2020
 */

package com.calendario.user.dto;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Add Comment dto")
public class CommentDto {

	private UUID postId;

	private UUID userId;

	private UUID parentCommentId;

	private String content;
}
