package cn.edu.csu.sample;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendReceiveSample {
	private ApplicationContext ctx;
	private ConnectionFactory factory;
	private Destination dest;
	private Connection conn;
	private Session session;
	
	public SendReceiveSample(){
		init();
	}
	
	public void init(){
		ctx = new ClassPathXmlApplicationContext("common.xml");
		factory = (ConnectionFactory) ctx.getBean("connectionFactory");
		dest = (Destination) ctx.getBean("destination");
	}
	
	public void send(String str){
		try {
			conn = factory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(dest);
			TextMessage msg = new ActiveMQTextMessage();
			msg.setText(str);
			sender.send(msg);
			System.out.println("Sent msg ======== " + str);
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

	public void receive(){
		try {
			conn = factory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer receiver = session.createConsumer(dest);
			TextMessage text = (TextMessage) receiver.receive();
			System.out.println("Received msg ======== " + text);
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendReceiveSample t = new SendReceiveSample();
		t.send("What a painful project!");
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		t.receive();
	}
}
