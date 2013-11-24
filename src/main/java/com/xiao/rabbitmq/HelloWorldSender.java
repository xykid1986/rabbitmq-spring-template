package com.xiao.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloWorldSender {
	
	private static String QUEUE_NAME = "hello";
	private static int counter = 1;

	public static void main(String[] args) throws IOException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("yi");
	    factory.setPassword("1986441986");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    String message = "Hello World!";
	    while(true){
	    	channel.basicPublish("", QUEUE_NAME, null, (message+counter).getBytes());//QueueName is used as routing key here
	    	 System.out.println(" [x] Sent '" + message + "'");
	    	Thread.sleep(1000);
	    	counter++;
	    	
	    }
	    //channel.close();
	    //connection.close();
	}

}
