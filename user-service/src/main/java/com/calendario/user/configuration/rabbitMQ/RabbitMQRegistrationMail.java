/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.configuration.rabbitMQ
 * @FileName: RabbitMQRegistrationMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 12:10:41 pm
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
public class RabbitMQRegistrationMail {

	String regdQueue = RabbitMQConstant.REGD_QUEUE;
	String regdExchange = RabbitMQConstant.REGD_EXCHANGE;
	String regdRouting = RabbitMQConstant.REGD_ROUTING;

	@Bean
	public Queue regdQueue() {
		return QueueBuilder.durable(regdQueue).build();
	}

	@Bean
	public TopicExchange regdExchange() {
		return new TopicExchange(regdExchange, true, false);
	}

	@Bean
	Binding bindRegdExchange() {
		return BindingBuilder.bind(regdQueue()).to(regdExchange()).with(regdRouting);
	}
}
