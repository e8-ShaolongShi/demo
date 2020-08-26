package jdk_async.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * spring-async 例子
 *
 * @author shishaolong
 * @datatime 2020/8/26 9:55
 */
@Component
public class AsyncExecutorExample2 {

    @Async
    public void printMessages() {
        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " message " + i);
        }
    }

    @Async
    public CompletableFuture<String> doSomething(){
        // 1.创建future
        CompletableFuture<String> result = new CompletableFuture<>();
        // 2.模拟任务执行
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " doSomething");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.complete("done");
        // 3.返回结果
        return result;
    }
}
