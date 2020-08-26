package jdk_async.spring;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * spring-async 例子
 *
 * @author shishaolong
 * @datatime 2020/8/26 9:55
 */
@Component
public class AsyncExecutorExample {

    private class MessagePrinterTask implements Runnable {

        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    @Getter
    private TaskExecutor taskExecutor;

    public void printMessages() {
        for (int i = 0; i < 6; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message " + i));
        }
    }

    public void shutdown() {
        if (taskExecutor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) taskExecutor).shutdown();
        }
    }
}
