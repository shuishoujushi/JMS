package cn.edu.csu.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Simple listener which monitor the dest queue
 * Only a connection factory and detination in spring configuration file
 * @author 1410267
 *
 */
public class SimpleListener implements MessageListener {
	private ApplicationContext ctx;
	private ConnectionFactory factory;
	private Destination dest;
	private Connection conn;
	private Session session;
	
	public SimpleListener(){
		init();
	}
	
	public void init(){
		ctx = new ClassPathXmlApplicationContext("common.xml");
		factory = (ConnectionFactory) ctx.getBean("connectionFactory");
		dest = (Destination) ctx.getBean("destination");
		try {
			conn = factory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void consumeMessage(){
		try {
			MessageConsumer receiver = session.createConsumer(dest);
			conn.start();
			receiver.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally{
			try {
				session.close();
				conn.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	public void onMessage(Message msg) {
		TextMessage text = (TextMessage) msg;
		System.out.println("Received msg = " + text);
	}
	
	public static void main(String[] args) {
		SimpleListener lr = new SimpleListener();
		lr.consumeMessage();
	}
}
