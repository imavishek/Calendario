/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.dto
 * @FileName: PostDto.java
 * @Author: Avishek Das
 * @CreatedDate: 15-07-2020
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
@ApiModel(description = "Create Post dto")
public class PostDto {

	private UUID userId;

	private String content;
}
