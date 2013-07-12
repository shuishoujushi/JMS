package cn.edu.csu.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Based on activemq and tomcat, used to moniter the message on RECEIVE queue and send it to REPLY queue.
 * @author 1410267
 *
 */
public class QueryListener implements MessageListener {
	
	private Destination replyQueue;
	private ConnectionFactory connectionFactory;
	private Connection conn;
	private Session session;
	private MessageProducer producer;
	
	public QueryListener(){
	}

	public void setReplyQueue(Destination replyQueue) {
		this.replyQueue = replyQueue;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void init(){
		try {
			conn = connectionFactory.createConnection();
			session = conn.createSession(false, 1);
			producer = session.createProducer(replyQueue);
			System.out.println("conn = " + conn + ", session = " + session + ", producer = " + producer);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy(){
		try {
			producer.close();
			session.close();
			conn.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onMessage(Message msg) {
		try {
			System.out.println("received msg ====== " + msg);
			producer.send(msg);
		} catch (JMSException e) {
			System.out.println("e = " + e);
			e.printStackTrace();
		}
		
	}

}
