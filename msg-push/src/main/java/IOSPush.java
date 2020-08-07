import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.eatthepath.pushy.apns.util.TokenUtil;
import com.eatthepath.pushy.apns.util.concurrent.PushNotificationFuture;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author shishaolong
 * @datatime 2020/8/6 15:17
 */
@Slf4j
public class IOSPush {
    //    private static final Logger logger = LoggerFactory.getLogger(IOSPush.class);
    private static ApnsClient apnsClient = null;
    private static final Semaphore semaphore = new Semaphore(10000);

    public void push(final List<String> deviceTokens, String alertTitle, String alertBody) throws IOException {
        for (String deviceToken : deviceTokens) {
            // 设置证书
            final ApnsClient apnsClient = new ApnsClientBuilder()
                    .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                    .setClientCredentials(new File("D:\\work\\certificate\\yh-app.p12"), "")
                    .build();

            // 构建推送消息体
            final SimpleApnsPushNotification pushNotification;
            {
                final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
                payloadBuilder.setAlertBody(alertBody);

                final String payload = payloadBuilder.build();
                final String token = TokenUtil.sanitizeTokenString(deviceToken);

                pushNotification = new SimpleApnsPushNotification(token, "com.Yinfanwan.staff", payload);
            }

            // 发送
            final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                    sendNotificationFuture = apnsClient.sendNotification(pushNotification);
            // 各种异常处理
            try {
                final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                        sendNotificationFuture.get();
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
            sendNotificationFuture.whenComplete((response, cause) -> {
                if (response != null) {
                    // Handle the push notification response as before from here.
                    log.info("Send Completed and Response: {}", response);
                } else {
                    // Something went wrong when trying to send the notification to the
                    // APNs server. Note that this is distinct from a rejection from
                    // the server, and indicates that something went wrong when actually
                    // sending the notification or waiting for a reply.
                    cause.printStackTrace();
                }
            });

            // 关闭资源 最好只是在程序关闭时再关闭资源
            final CompletableFuture<Void> closeFuture = apnsClient.close();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        IOSPush iosPush = new IOSPush();
        List<String> deviceTokenList = new ArrayList();
        deviceTokenList.add("<8dcadc64 8d802c83 368ec91e 7aeab46e a5085c4d 298e0464 5279712a 9cd0264b>");
        iosPush.push(deviceTokenList, "测试", "这是内容");
        Thread.sleep(4000);
    }
}
