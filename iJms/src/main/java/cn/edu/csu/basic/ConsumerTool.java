package cn.edu.csu.basic;

import javax.jms.Connection;     
import javax.jms.Destination;     
import javax.jms.JMSException;     
import javax.jms.MessageConsumer;     
import javax.jms.Session;     
import javax.jms.MessageListener;     
import javax.jms.Message;     
import javax.jms.TextMessage;     
    
import org.apache.activemq.ActiveMQConnection;     
import org.apache.activemq.ActiveMQConnectionFactory;     
    
public class ConsumerTool implements MessageListener {     
    
    private String user = ActiveMQConnection.DEFAULT_USER;     
    
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;     
    
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;     
    
    private String subject = "TOOL.DEFAULT";     
    
    private Destination destination = null;     
    
    private Connection connection = null;     
    
    private Session session = null;     
    
    private MessageConsumer consumer = null;     
    
    private void initialize() throws JMSException, Exception {  
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);   
        connection = connectionFactory.createConnection();     
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
        destination = session.createQueue(subject);   
        consumer = session.createConsumer(destination);     
             
    }     
    
    public void consumeMessage() throws JMSException, Exception {     
        initialize();     
        connection.start();     
             
        System.out.println("Consumer:->Begin listening...");     
        consumer.setMessageListener(this);     
        // Message message = consumer.receive();     
    }     
    
    public void close() throws JMSException {     
        System.out.println("Consumer:->Closing connection");     
        if (consumer != null)     
            consumer.close();     
        if (session != null)     
            session.close();     
        if (connection != null)     
            connection.close();     
    }     
    
    public void onMessage(Message message) {     
        try {     
            if (message instanceof TextMessage) {     
                TextMessage txtMsg = (TextMessage) message;     
                String msg = txtMsg.getText();     
                System.out.println("Consumer:->Received: " + msg);     
            } else {     
                System.out.println("Consumer:->Received: " + message);     
            }     
        } catch (JMSException e) {     
            // TODO Auto-generated catch block     
            e.printStackTrace();     
        }     
    }     
}   