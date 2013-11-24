package com.xiao.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class HelloWorldReceiver {
	
	 private final static String QUEUE_NAME = "hello";
	 

	 
	 public static class Worker implements Runnable {

		 
		public QueueingConsumer consumer;
		
		public Worker(QueueingConsumer consumer){
			this.consumer = consumer;
		}
		
		@Override
		public void run() {
			doWork();
		}
		
		public void doWork(){
			while(true){
				QueueingConsumer.Delivery delivery = null;
				try {
					delivery = consumer.nextDelivery();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			    String message = new String(delivery.getBody());
				System.out.println("Workder" + Thread.currentThread().getName()+" is working on "+message);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	 }
	  public static void main(String[] argv)
	      throws java.io.IOException,
	             java.lang.InterruptedException {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("yi");
	    factory.setPassword("1986441986");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(QUEUE_NAME, true, consumer);

	    int nWorkers = 3;
	    
	    for(int i=0;i<nWorkers;i++){
	    	new Thread(new Worker(consumer)).start();
	    }
	    /*
	    while (true) {
	      Thread.sleep(1200);
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());
	      System.out.println(" [x] Received '" + message + "'");
	    }
	    */
	    }
}
