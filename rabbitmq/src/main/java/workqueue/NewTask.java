package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * @author shishaolong
 * @datatime 2020/6/17 10:47
 */
public class NewTask {

    /**
     * 定义消息名称
     */
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {

        // 创建工厂， 工厂 -> 连接  -> channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 申明队列 参数1：队列名称， 参数2：是否持久化
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            // 定义消息【任务】
//            String message = String.join(" ", args);
            String message = "Fifth message.....";

            // 发送消息
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8.name()));

            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
