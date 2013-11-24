package com.xiao.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.Message;

public class QueueListener {
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void listen(Object message){
		if(message instanceof byte[])
			System.out.println(new Date()+": "+name+" received "+new String((byte[])message));
	}
}
