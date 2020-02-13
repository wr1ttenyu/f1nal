package wr1ttenyu.study.f1nal.springboot.demo.web;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.*;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void springBean() {

	}

	public static void main(String[] args) throws JMSException {
		/*ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://122.51.219.124:61616");
		Connection connection = mqConnectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue wr1ttenyu = session.createQueue("wr1ttenyu");
		MessageProducer producer = session.createProducer(wr1ttenyu);

		TextMessage hello = session.createTextMessage("hello");
		producer.send(hello);

		producer.close();
		session.close();
		connection.close();*/
		TestBoolean testBoolean = new TestBoolean();
		testBoolean.setField1(false);
	}
}
class TestBoolean {
	private Boolean field1;

	public Boolean getField1() {
		return field1;
	}

	public void setField1(Boolean field1) {
		this.field1 = field1;
	}
}
