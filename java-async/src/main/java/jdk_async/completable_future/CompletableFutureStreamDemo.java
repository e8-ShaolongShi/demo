package jdk_async.completable_future;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * CompletableFuture 与 JDK8-stream相结合的例子
 *
 * @author shishaolong
 * @datatime 2020/8/25 16:25
 */
public class CompletableFutureStreamDemo {

    /**
     * rpc 调用
     *
     * @param ip
     * @param param
     * @return
     */
    private String rpcCall(String ip, String param) {
        System.out.println(ip + "rpcCall:" + param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    /**
     * CompletableFuture 与 Stream结合，一般是在Stream中间操作中将同步任务转成异步操作
     */
    @Test
    public void demo01() {
        // 1. 生生ip列表
        List<String> ipList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ipList.add("192.168.0." + i);
        }

        // 2.发起广播调用
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> futureList = ipList.stream()
                .map(ip -> CompletableFuture.supplyAsync((() -> rpcCall(ip, ip))))
                .collect(Collectors.toList());

        // 3.等待所有任务完成
        List<String> resultList = futureList.stream().map(future -> future.join()).collect(Collectors.toList());

        // 4.输出
        resultList.stream().forEach(System.out::println);
        System.out.println("Cost:" + (System.currentTimeMillis() - start));
    }

}
