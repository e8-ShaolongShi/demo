package jdk_async.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author shishaolong
 * @datatime 2020/8/26 10:07
 */
@RestController
@RequestMapping("api")
public class ExampleController {

    @Autowired
    private AsyncExecutorExample2 asyncExecutorExample;

    @GetMapping("test")
    public void test() {
        System.out.println(Thread.currentThread().getName() + " -- start");
        asyncExecutorExample.printMessages();
        System.out.println(Thread.currentThread().getName() + " -- end ");
    }

    @GetMapping("test2")
    public void test2() {
        System.out.println(Thread.currentThread().getName() + " -- start");
        CompletableFuture resultFuture = asyncExecutorExample.doSomething();
        resultFuture.whenComplete((res, cause) -> {
            if (cause == null) {
                System.out.println(Thread.currentThread().getName() + " " + res);
            } else {
                System.out.println("error:" + ((Throwable) cause).getLocalizedMessage());
            }
        });
        System.out.println(Thread.currentThread().getName() + " -- end ");
    }
}
