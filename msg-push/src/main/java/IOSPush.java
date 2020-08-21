import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.eatthepath.pushy.apns.util.TokenUtil;
import com.eatthepath.pushy.apns.util.concurrent.PushNotificationFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shishaolong
 * @datatime 2020/8/6 15:17
 */
@Slf4j
public class IOSPush {
    private static ApnsClient apnsClient = null;
    private static final Semaphore semaphore = new Semaphore(10000);

    public void push(final List<String> deviceTokens, String alertTitle, String alertBody) throws IOException {

        // 初始化APNs client
        if (apnsClient == null) {
            try {
                EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
                apnsClient = new ApnsClientBuilder()
                        .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                        // 设置证书
                        .setClientCredentials(new File("D:\\work\\certificate\\yh-app.p12"), "")
                        .setConcurrentConnections(4).setEventLoopGroup(eventLoopGroup)
                        .build();

            } catch (Exception e) {
                log.error("ios get pushy APNs client failed!");
                e.printStackTrace();
            }
        }

        // 遍历每一个deviceToken 发送
        for (String deviceToken : deviceTokens) {
            // 构建推送消息体
            final SimpleApnsPushNotification pushNotification;
            {
                final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
                payloadBuilder.setAlertBody(alertBody);
                payloadBuilder.setAlertTitle(alertTitle);

                final String payload = payloadBuilder.build();
                final String token = TokenUtil.sanitizeTokenString(deviceToken);
                // 第一个参数： deviceToken， 第二个参数： buildId， 第三个参数： 发送的内容
                pushNotification = new SimpleApnsPushNotification(token, "com.Yinfanwan.staff", payload);
            }

            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                log.debug("ios push semaphore.acquire() is error");
                e.printStackTrace();
            }

            // 发送
            final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>> sendFuture = apnsClient.sendNotification(pushNotification);

            // 各种异常处理
            try {
                final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                        sendFuture.get();
                // APNs接受了推送的消息
                if (pushNotificationResponse.isAccepted()) {
                    System.out.println("Push notification accepted by APNs gateway.");
                } else {
                    // APNs 决绝接受推送 有失败原因  这应视为永久性失败，不需要再尝试推送。
                    System.out.println("Notification rejected by the APNs gateway: " +
                            pushNotificationResponse.getRejectionReason());
                    // 令牌无效 不需要再次尝试发送
                    pushNotificationResponse.getTokenInvalidationTimestamp().ifPresent(timestamp -> {
                        System.out.println("\t…and the token is invalid as of " + timestamp);
                    });
                }

            } catch (final ExecutionException | InterruptedException e) {
                // 这个是由异常引起失败， 当问题解决了可尝试二次发送  记录下来二次发送
                System.err.println("Failed to send push notification.");
                e.printStackTrace();
            }

            // 推送完成后需要执行的操作
            sendFuture.whenComplete((response, cause) -> {
                if (response != null) {
                    log.info("Send Completed and Response: {}", response);
                } else {
                    cause.printStackTrace();
                }
                semaphore.release();
            });
        }

//             关闭资源 最好只是在程序关闭时再关闭资源
        final CompletableFuture<Void> closeFuture = apnsClient.close();
    }



    public static void main(String[] args) throws IOException, InterruptedException {
        IOSPush iosPush = new IOSPush();
        List<String> deviceTokenList = new ArrayList();
//        String deviceToken = "<8dcadc64 8d802c83 368ec91e 7aeab46e a5085c4d 298e0464 5279712a 9cd0264b>";
        String deviceToken = "<8dcadc64 8d802c83 368ec91e 7aeab46e a5085c4d 298e0464 5279712a 9cd0264b   52>  ";
        deviceTokenList.add(deviceToken);
        deviceTokenList.add(deviceToken);
        iosPush.push(deviceTokenList, "测试", "这是内容");
//        Thread.sleep(4000);
    }
}
