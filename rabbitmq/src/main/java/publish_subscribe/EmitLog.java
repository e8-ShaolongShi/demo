package publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * 生产者：提交日志
 *
 * @author shishaolong
 * @datatime 2020/6/24 14:26
 */
public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 声明 exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = args.length < 1 ? "info: hello world" : String.join(" ", args);

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8.name()));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
