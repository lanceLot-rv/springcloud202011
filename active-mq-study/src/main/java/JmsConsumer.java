import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQXAConnectionFactory;

import javax.jms.*;

public class JmsConsumer {
    public static  final String ACTIVEMQ_URL="tcp://192.168.169.129:61616";
    public static  final String QUEUE_NAME="QUEUE01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer =session.createConsumer(queue);
        while(true){
            TextMessage textMessage = (TextMessage) messageConsumer.receive(4000L);
            if(null!=textMessage){
                System.out.println("######收到消息"+textMessage.getText());
            }else {
                break;
            }
        }

        messageConsumer.close();
        session.close();
        connection.close();
    }
}
