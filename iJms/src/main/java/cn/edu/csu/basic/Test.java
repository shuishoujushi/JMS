package cn.edu.csu.basic;
import javax.jms.JMSException;     
import org.apache.activemq.ActiveMQConnection;  
public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws JMSException, Exception {
		// TODO Auto-generated method stub
		ConsumerTool consumer = new ConsumerTool();     
        ProducerTool producer = new ProducerTool();    
        System.out.println(ActiveMQConnection.DEFAULT_BROKER_URL+"------------");  
        consumer.consumeMessage();     
             
        Thread.sleep(500);     
        producer.produceMessage("Hello, world!");     
        producer.close();     
             
        Thread.sleep(500);     
        consumer.close();  
	}

}
