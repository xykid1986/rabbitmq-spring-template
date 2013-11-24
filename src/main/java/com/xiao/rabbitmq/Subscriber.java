package com.xiao.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Subscriber {
	
	private static String exchangeName = "round_robin";
	private static String exchangeType = "direct";

	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("yi");
	    factory.setPassword("1986441986");
	    Connection connection = factory.newConnection();
	    
	    Channel channel = connection.createChannel();
	    channel.exchangeDeclare(exchangeName,exchangeType);
	    
	    String[] queues = new String[3];
	    QueueingConsumer[] consumers = new QueueingConsumer[3];
	    
	    for(int i=0;i<3;i++){
	    	queues[i] = "queue "+i;
	    	channel.queueDeclare("queue "+i,false,false,false,null);
	    }
	    for(int i=0;i<3;i++){
	    	channel.queueBind(queues[i],exchangeName,queues[i]);//third arg is routekey,use "" for fanout exchange
	    	consumers[i] = new QueueingConsumer(channel);
	    	channel.basicConsume(queues[i], true, consumers[i]);
	    }
	    
	    while (true) {
	    	for(int i=0;i<3;i++){
	    		QueueingConsumer.Delivery delivery = consumers[i].nextDelivery();
	    		String message = new String(delivery.getBody());
	    		System.out.println(queues[i]+" Received '" + message + "'");
	    	}
	    	
        }
	    
	}

}
