package wr1ttenyu.study.f1nal.springboot.demo.web;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.*;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*@SpringBootTest*/
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void springBean() {

	}

	public static void main(String[] args) throws JMSException, IOException {
		ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://122.51.219.124:61616");
		Connection connection = mqConnectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue wr1ttenyu = session.createQueue("wr1ttenyu.queue");
		MessageProducer producer = session.createProducer(wr1ttenyu);

		TextMessage hello = session.createTextMessage("hello");
		producer.send(hello);

		Session session1 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session1.createConsumer(wr1ttenyu);
		consumer.setMessageListener((msg)->{
			TextMessage text = (TextMessage)msg;
			System.out.println(text);
		});
		System.in.read();

		producer.close();
		consumer.close();
		session.close();
		connection.close();
	}
}
