/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.configuration.rabbitMQ
 * @FileName: RabbitMQRegistrationSuccessMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 2:10:27 pm
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
public class RabbitMQRegistrationSuccessMail {

	String regdSuccessQueue = RabbitMQConstant.REGD_SUCCESSFUL_QUEUE;
	String regdSuccessExchange = RabbitMQConstant.REGD_SUCCESSFUL_EXCHANGE;
	String regdSuccessRouting = RabbitMQConstant.REGD_SUCCESSFUL_ROUTING;

	@Bean
	public Queue regdSuccessQueue() {
		return QueueBuilder.durable(regdSuccessQueue).build();
	}

	@Bean
	public TopicExchange regdSuccessExchange() {
		return new TopicExchange(regdSuccessExchange, true, false);
	}

	@Bean
	Binding bindRegdSuccessExchangee() {
		return BindingBuilder.bind(regdSuccessQueue()).to(regdSuccessExchange()).with(regdSuccessRouting);
	}
}
