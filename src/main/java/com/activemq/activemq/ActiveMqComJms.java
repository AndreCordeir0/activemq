package com.activemq.activemq;

import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;

public class ActiveMqComJms {


    public static void main(String[] args) {
        ActiveMqComJms.run();
        ActiveMqComJms.consumer();
    }
    public static void run(){
        try {

            JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
            Connection connection = factory.createConnection("admin", "admin");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("filaHelloWorld");

            MessageProducer producer = session.createProducer(destination);

            TextMessage msg = session.createTextMessage("Hello World");

            producer.send(msg);

            connection.close();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    public static void consumer(){
        try {
            JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
            Connection connection = factory.createConnection("admin", "admin");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("filaHelloWorld");

            MessageConsumer consumer = session.createConsumer(destination);
            TextMessage text = (TextMessage) consumer.receive(1000);
            System.out.println(text.getText());
            connection.close();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
