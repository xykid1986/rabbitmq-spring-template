package com.xiao.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MyListener implements MessageListener{

	public static String name ="myListener";
	
	@Override
	public void onMessage(Message message) {
		String correlationId = new String(message.getMessageProperties().getCorrelationId());
		String replyTo = new String(message.getMessageProperties().getReplyTo());
		System.out.println(new Date()+": "+name+" received "+new String(message.getBody())+" corId:"+correlationId+" replyTo "+replyTo);
	}

}
