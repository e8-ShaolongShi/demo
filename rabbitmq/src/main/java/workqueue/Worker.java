package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author shishaolong
 * @datatime 2020/6/4 17:51
 */
public class Worker {

    /**
     * 定义消息名称
     */
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        // factory -> connection -> channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // 申明队列
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 设置调度策略，只有当消费者完成任务，才会给它再分任务
        channel.basicQos(1);

        // 定义消息处理逻辑
        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            // 获取消息
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8.name());
            System.out.println(" [x] Received '" + message + "'");

            try {
                doWork(message);
            }finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        // 将消息处理绑定到channel上 第二个参数为自己控制消息应答
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {});
    }

    /**
     * 模拟做多久工作
     * @param task
     * @throws InterruptedException
     */
    private static void doWork(String task){
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
