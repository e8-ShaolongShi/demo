package jdk_async.futures;

import java.util.concurrent.*;

/**
 * demo2：使用线程池
 *
 * @author shishaolong
 * @datatime 2020/8/24 9:48
 */
public class AsyncFutureExample2 {

    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POLL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS, AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        //  创建future任务
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            String result = null;
            try {
                result = AsyncFutureExample.doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 2.开启异步单元执行任务A
        POLL_EXECUTOR.execute(futureTask);

        // 3.执行任务B
        String taskBResult = AsyncFutureExample.doSomethingB();

        // 4.同步等待线程A运行结束
        String taskAResult = futureTask.get();
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);
    }

}
