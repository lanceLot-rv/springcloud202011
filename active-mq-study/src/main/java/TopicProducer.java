import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;

import javax.jms.*;

public class TopicProducer {
    public static  final String ACTIVEMQ_URL="tcp://192.168.169.129:61616";
    public static  final String TOPIC_NAME="TOPIC01";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topic);

        for (int i = 1; i <= 6; i++) {
            TextMessage textMessage = session.createTextMessage("cherish " + i);

            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*******消息发布到MQ完成");
    }
}
