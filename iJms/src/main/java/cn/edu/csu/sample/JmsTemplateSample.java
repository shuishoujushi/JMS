package cn.edu.csu.sample;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

public class JmsTemplateSample extends JmsGatewaySupport {
	public JmsTemplateSample(){
		init();
	}
	public void init(){
	}
	public void send(final String msg){
		getJmsTemplate().send(new MessageCreator() {
			public Message createMessage(Session paramSession) throws JMSException {
				System.out.println("Send msg ======== " + msg);
				return paramSession.createTextMessage(msg);
			}
		});
	}
	
	public void receive(){
		TextMessage txt = (TextMessage) getJmsTemplate().receive();
		try {
			String id = txt.getJMSMessageID();
			String text = txt.getText();
			System.out.println("MessageId ========= " + id);
			System.out.println("MessageContent ======== " + text);
//			System.out.println(txt);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jmstemplate-context.xml");
		JmsTemplateSample t = (JmsTemplateSample) ctx.getBean("jmsTemplateSample");
		System.out.println(t);
		for(int i = 0; i < 1; i++){
			String msg = "hello " + i;
			t.send(msg);
		}
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		t.receive();
	}
}
