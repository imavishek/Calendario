/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.constant
 * @FileName: UserServiceEndpointUrl.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 7:07:22 pm
 */

package com.calendario.global.common.microservice.constant;

public class UserServiceEndpointUrl {

	public static final String REGISTER_USER = "/user/registration";

	public static final String REGISTRATION_CONFIRM = "/user/registrationConfirm";

	public static final String VALIDATE_REGISTER_USER_TOKEN = "/user/validateRegistrationToken/{token}";

	public static final String CREATE_AVAILABLE_SLOT = "/slot/create";

	public static final String GET_AVAILABLE_SLOT = "/slot/get/{userId}";

	public static final String CREATE_EVENT = "/event/create";

	public static final String GET_EVENT = "/event/get/{eventId}";

	public static final String CREATE_POST = "/post/create";

	public static final String GET_POST = "/post/get/{postId}";

	public static final String GET_POST_BY_USERID = "/post/getByUserId/{userId}";

	public static final String ADD_COMMENT = "/comment/add";

	public static final String GET_COMMENT_BY_POSTID = "/comment/getParentCommentByPostId/{postId}";

	public static final String GET_CHILD_COMMENT = "/comment/getChildComments/{commentId}";
}
