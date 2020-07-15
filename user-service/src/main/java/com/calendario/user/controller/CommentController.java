/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.controller
 * @FileName: CommentController.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2020
 */

package com.calendario.user.controller;

import com.calendario.global.common.microservice.constant.UserServiceEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.dto.CommentDto;
import com.calendario.user.entities.Comment;
import com.calendario.user.entities.Post;
import com.calendario.user.entities.User;
import com.calendario.user.properties.MessageProperties;
import com.calendario.user.repository.CommentRepository;
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
@Api(tags = "User Post Comment API", description = "Handle post comment operation")
public class CommentController {

	@Autowired
	private MessageProperties messageProperties;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method is used as a endpoint to add comment.
	 *
	 * @param commentDto : Object containing the content of comment fields.
	 * @return Response : Object containing the response status, message and data.
	 */
	@PostMapping(value = UserServiceEndpointUrl.ADD_COMMENT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Post")
	public Response<Object> add(@RequestBody CommentDto commentDto) throws CalendarioNotFoundApiException {

		String message = null;
		Status status = null;

		Post post = postRepository.findById(commentDto.getPostId())
				.orElseThrow(() -> new CalendarioNotFoundApiException("Post not found. PostId: " + commentDto.getPostId()));

		User user = userRepository.findById(commentDto.getUserId())
				.orElseThrow(() -> new CalendarioNotFoundApiException("User not found. UserId: " + commentDto.getUserId()));

		Comment comment = new Comment();
		comment.setPost(post);
		comment.setUser(user);
		comment.setContent(commentDto.getContent());

		if(commentDto.getParentCommentId() != null) {
			Comment parentComment = commentRepository.findById(commentDto.getParentCommentId())
					.orElseThrow(() -> new CalendarioNotFoundApiException("Parent Comment not found. ParentCommentId: " + commentDto.getParentCommentId()));

			comment.setParentComment(parentComment);
		}

		comment = commentRepository.save(comment);

		if(comment.getCommentId() != null) {
			status = Status.C_1;
			message = messageProperties.getComment_add_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getComment_add_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}

	/**
	 * Method is used as a endpoint to get comments by postId.
	 *
	 * @param postId
	 * @return Response : Object containing the response status, message and data.
	 */
	@GetMapping(value = UserServiceEndpointUrl.GET_COMMENT_BY_POSTID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get parent comments by postId")
	public Response<Object> getParentComment(@PathVariable UUID postId, Pageable pageable) {

		String message = null;
		Status status = Status.C_1;

		Page<Comment> comments = commentRepository.findAllByPostPostIdAndParentCommentCommentId(postId, null, pageable);

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, comments);
	}

	/**
	 * Method is used as a endpoint to get child comments.
	 *
	 * @param commentId
	 * @return Response : Object containing the response status, message and data.
	 */
	@GetMapping(value = UserServiceEndpointUrl.GET_CHILD_COMMENT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get child comments")
	public Response<Object> getChildComment(@PathVariable UUID commentId, Pageable pageable) {

		String message = null;
		Status status = Status.C_1;

		Page<Comment> comments = commentRepository.findAllByParentCommentCommentId(commentId, pageable);

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, comments);
	}
}
