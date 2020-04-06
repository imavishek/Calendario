/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.constant
 * @FileName: RabbitMQConstant.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:07:17 pm
 */

package com.calendario.global.common.microservice.constant;

public class RabbitMQConstant {

	public static final String REGD_QUEUE = "regd-queue";
	public static final String REGD_EXCHANGE = "regd-exchange";
	public static final String REGD_ROUTING = "regd-routing";

	public static final String REGD_SUCCESSFUL_QUEUE = "regd-success-queue";
	public static final String REGD_SUCCESSFUL_EXCHANGE = "regd-success-exchange";
	public static final String REGD_SUCCESSFUL_ROUTING = "regd-success-routing";

	public static final String EVENT_NOTIFICATION_QUEUE = "event-notification-queue";
	public static final String EVENT_NOTIFICATION_EXCHANGE = "event-notification-exchange";
	public static final String EVENT_NOTIFICATION_ROUTING = "event-notification-routing";
}
