package jdk_async.completable_future;

import java.util.concurrent.*;

/**
 * CompletableFuture测试
 *
 * @author shishaolong
 * @datatime 2020/8/25 9:45
 */
public class TestCompletableFutureSet {
    /**
     * 自定义线程
     */
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS, AVALIABLE_PROCESSORS * 2, 1, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 创建CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<>();

        // 2.开启线程计算任务结果并设置
        POOL_EXECUTOR.execute(() -> {
            // 2.1休眠3s， 模拟任务计算
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //2.2设置计算结果到future
            System.out.println("----------" + Thread.currentThread().getName() + " set future result ----------");
            future.complete("Hello test!");
        });

        // 3.等待计算结果
        System.out.println("=============== main thread wait future result =================");
        System.out.println(future.get());
        System.out.println(future.get(1000, TimeUnit.SECONDS));
        System.out.println("=============== main thread got future result ==============");
    }
}
