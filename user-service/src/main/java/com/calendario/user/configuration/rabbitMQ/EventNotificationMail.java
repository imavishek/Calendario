/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.configuration.rabbitMQ
 * @FileName: EventNotificationMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 9:54:01 pm
 */

package com.calendario.user.configuration.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.calendario.global.common.microservice.constant.RabbitMQConstant;

@Configuration
public class EventNotificationMail {

	String eventNotificationQueue = RabbitMQConstant.EVENT_NOTIFICATION_QUEUE;
	String eventNotificationExchange = RabbitMQConstant.EVENT_NOTIFICATION_EXCHANGE;
	String eventNotificationRouting = RabbitMQConstant.EVENT_NOTIFICATION_ROUTING;

	@Bean
	public Queue eventNotificationQueue() {
		return QueueBuilder.durable(eventNotificationQueue).build();
	}

	@Bean
	public TopicExchange eventNotificationExchange() {
		return new TopicExchange(eventNotificationExchange, true, false);
	}

	@Bean
	Binding bindEventNotificationExchange() {
		return BindingBuilder.bind(eventNotificationQueue()).to(eventNotificationExchange())
				.with(eventNotificationRouting);
	}
}
