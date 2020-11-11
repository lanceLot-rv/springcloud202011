import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer {
    public static  final String ACTIVEMQ_URL="tcp://192.168.169.129:61616";
    public static  final String TOPIC_NAME="TOPIC01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.setClientID("cherish-flower");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic =session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"my-mq");
        connection.start();
        Message message = null;
         do{ message = topicSubscriber.receive();
            TextMessage textMessage =(TextMessage)message;
            System.out.println("##### topic message" + textMessage.getText());
        } while (null!=message &&message instanceof TextMessage );
        System.in.read();
        topicSubscriber.close();
        session.close();
        connection.close();


    }
}

