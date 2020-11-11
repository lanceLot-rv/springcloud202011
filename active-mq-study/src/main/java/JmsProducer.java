import org.apache.activemq.ActiveMQXAConnectionFactory;

import javax.jms.*;

public class JmsProducer {
    public static  final String ACTIVEMQ_URL="tcp://192.168.169.129:61616";
    public static  final String QUEUE_NAME="QUEUE01";

    public static void main(String[] args) throws JMSException {
        ActiveMQXAConnectionFactory factory = new ActiveMQXAConnectionFactory(ACTIVEMQ_URL);
        Connection connection =factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);

        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("cherish " + i);

            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*******消息发布到MQ完成");
    }
}
