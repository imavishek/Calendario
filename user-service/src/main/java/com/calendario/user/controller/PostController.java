/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.controller
 * @FileName: PostController.java
 * @Author: Avishek Das
 * @CreatedDate: 15-07-2020
 */

package com.calendario.user.controller;

import com.calendario.global.common.microservice.constant.UserServiceEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.dto.PostDto;
import com.calendario.user.entities.Post;
import com.calendario.user.entities.User;
import com.calendario.user.properties.MessageProperties;
import com.calendario.user.repository.PostRepository;
import com.calendario.user.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RefreshScope
@Api(tags = "User Post API", description = "Handle post operation")
public class PostController {

	@Autowired
	private MessageProperties messageProperties;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method is used as a endpoint to create post.
	 *
	 * @param postDto : Object containing the content of post fields.
	 * @return Response : Object containing the response status, message and data.
	 */
	@PostMapping(value = UserServiceEndpointUrl.CREATE_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Post")
	public Response<Object> create(@RequestBody PostDto postDto) throws CalendarioNotFoundApiException {

		String message = null;
		Status status = null;

		User user = userRepository.findById(postDto.getUserId())
				.orElseThrow(() -> new CalendarioNotFoundApiException("User not found. UserId: " + postDto.getUserId()));

		Post post = new Post();
		post.setUser(user);
		post.setContent(postDto.getContent());

		post = postRepository.save(post);

		if(post.getPostId() != null) {
			status = Status.C_1;
			message = messageProperties.getPost_create_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getPost_create_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}

	/**
	 * Method is used as a endpoint to get post by postId.
	 *
	 * @param postId
	 * @return Response : Object containing the response status, message and data.
	 * @throws CalendarioNotFoundApiException
	 */
	@GetMapping(value = UserServiceEndpointUrl.GET_POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get post by postId")
	public Response<Object> getPost(@PathVariable UUID postId) throws CalendarioNotFoundApiException {

		String message = null;
		Status status = Status.C_1;

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new CalendarioNotFoundApiException("Post not found. PostId: " + postId));

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, post);
	}

	/**
	 * Method is used as a endpoint to get post by userId.
	 *
	 * @param userId
	 * @return Response : Object containing the response status, message and data.
	 */
	@GetMapping(value = UserServiceEndpointUrl.GET_POST_BY_USERID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get post by postId")
	public Response<Object> getPostsByUserId(@PathVariable UUID userId, Pageable pageable) {

		String message = null;
		Status status = Status.C_1;

		Page<Post> posts = postRepository.findAllByUserUserId(userId, pageable);

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, posts);
	}
}
