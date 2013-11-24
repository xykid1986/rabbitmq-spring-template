package com.xiao.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
	
	private static int counter = 1;
	private static String exchangeName = "round_robin";
	private static String exchangeType = "direct";

	public static void main(String[] args) throws IOException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("yi");
	    factory.setPassword("1986441986");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.exchangeDeclare(exchangeName,exchangeType);
	    String message = "Message ";
	    while(true){
	    	channel.basicPublish(exchangeName, "queue "+counter%4, null, (message+counter).getBytes());//QueueName is used as routing key here
	    	 System.out.println(" [x] Sent '" + message + "'"+counter);
	    	Thread.sleep(1000);
	    	counter++;
	    }
	}

}
