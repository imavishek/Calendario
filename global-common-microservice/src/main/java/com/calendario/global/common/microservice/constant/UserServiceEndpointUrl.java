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
}
