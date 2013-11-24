package com.xiao.rabbitmq;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiveMessageTest {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:app-context.xml");
		RabbitTemplate template = ctx.getBean(RabbitTemplate.class); 
		Object receive = template.receiveAndConvert("myQueue");
		System.out.println(receive.getClass().getName());
	}

}
