package com.xiao.rabbitmq;

import java.util.UUID;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiao.domain.Product;

public class SpringAMQPTHelloWorld {

	private static int counter = 1;
	
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:app-context.xml");
		RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
		MessageProperties ms = new MessageProperties();
		ms.setReplyTo("replyQueue");
		
	    while(true){
	    	ms.setCorrelationId(UUID.randomUUID().toString().getBytes());
	    	Product p = new Product();
	    	p.setName("Product-"+counter);
	    	p.setPrice((int)(Math.random()*100));
	    	if(counter++%2==1)
	    		template.convertAndSend("foo",template.getMessageConverter().toMessage(p, ms));
	    	else
	    		template.convertAndSend("bar",template.getMessageConverter().toMessage(p, ms));
	    	Thread.sleep(2000);
	    }
	}

}
