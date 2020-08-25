package jdk_async.completable_future;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CompletableFuture 使用列子
 *
 * @author shishaolong
 * @datatime 2020/8/25 10:13
 */
public class CompletableDemo {

    /* =================== 01.创建型的，类似于流中的stream(),Steam.of()等方法 ===================== */

    /**
     * demo01： 测试runAsync()方法
     */
    @Test
    public void demo01() throws ExecutionException, InterruptedException {
        // 1.1 创建异步任务， 并返回future
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            // 休眠一会模拟任务计算
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task over!");
        });
        // 1.2 同步等待异步任务执行结束
        System.out.println(future.get());
    }

    /**
     * 自定义业务线程池
     */
    private static final ThreadPoolExecutor bizPoolExecutor =
            new ThreadPoolExecutor(8, 8, 1, TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(10));

    /**
     * demo02： 测试runAsync 传入自定义的线程池
     */
    @Test
    public void demo02() throws ExecutionException, InterruptedException {
        // 1.创建异步任务，并设置返回Future
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            // 休眠一会
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 任务结束
            System.out.println(" Task over!!! ");
        }, bizPoolExecutor);
        // 2.同步等待异步任务执行结束
        System.out.println(future.get());
    }

    /**
     * demo03: 创建有返回值的异步任务
     */
    @Test
    public void demo03() throws ExecutionException, InterruptedException {
        // 1.创建异步任务，并返回future
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            // 模拟任务计算，消耗一会时间
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 返回计算结果
            return "Hello, supplyAsync";
        });
        // 2.返回异步计算结果
        System.out.println(future.get());
    }

    /**
     * demo04 supplyAsync() 使用自定义线程池版
     */
    @Test
    public void demo04() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync 自定义线程池";
        }, bizPoolExecutor);
        System.out.println(future.get());
    }

    /*============================ 02.中间操作，类似流中的peek、filter、map等 ============================*/

    /**
     * 中间操作：thenRun()：只保证执行顺序，在第一个future计算完成后执行， 不需要前一个Future的计算结果，也不返回计算结果，
     * 单纯的只是执行任务   void run(){// 代码}; 无参无返回值
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void demo05() throws ExecutionException, InterruptedException {
        // 1.创建异步任务，并返回future
        CompletableFuture oneFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("------- oneFuture is running！ ------");
            return "hello";
        });

        // 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture twoFuture = oneFuture.thenRun(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("------- after oneFuture over twoFuture.get() ------");
        });

        // 3.同步等待twoFuture对应任务完成，返回结果固定位null
        System.out.println(twoFuture.get());
    }

    /**
     * 中间操作：thenAccept():保证执行顺序，在第一个future之后执行，能取到第一个的计算结果，但不返回执行结果
     * 相当于方法接口中的Consumer类的方法 void run(T tt){//代码} 有参无返回值
     */
    @Test
    public void demo06() throws ExecutionException, InterruptedException {
        // 1.创建第一个异步任务
        CompletableFuture oneFuture = CompletableFuture.supplyAsync(() -> {
            // 1.1 休眠2秒
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" ----------- oneFuture is running!!! ----------- ");
            // 2.2 返回计算结果
            return "Hello, thenAccept()!!!";
        });
        // 2.创建第二个异步任务，在第一个执行后执行
        CompletableFuture twoFuture = oneFuture.thenAccept(t -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" ----------- after oneFuture over doSomething, t: " + t + " ----------- ");
        });

        // 3.同步等待第二个异步任务执行结束，返回固定结果null
        System.out.println(twoFuture.get());
    }

    /**
     * 中间操作：thenApply，保证执行顺序，在前一个Future执行后执行，能取到前一个future的计算结果，也向下一个Future传递计算结果
     * 相当于function包中 Function方法接口。T function(A a)  接受A类型的参数，返回B类型的结果
     */
    @Test
    public void demo07() throws ExecutionException, InterruptedException {
        // 1.创建第一个异步任务，并返回future
        CompletableFuture oneFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("onwFuture is Running!!!");
            return "Hello thenApply!!!";
        });

        // 2.创建第二个异步任务，在第一个future执行完执行（回调的方式），并返回包装的新结果的Future
        CompletableFuture twoFuture = oneFuture.thenApply(t -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("twoFuture is running!!!");
            return t + " 雷无桀";
        });

        // 3.同步等待towFuture对应的任务完成，并获取结果
        System.out.println(twoFuture.get());
    }

    /**
     * 终端操作：whenComplete。当异步任务执行完毕后进行回调，不会阻塞调用线程
     */
    @Test
    public void demo08() throws InterruptedException {
        // 1.创建一个CompletableFuture
        CompletableFuture oneFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("one future is Running!!!");
            return "hello whenComplete()";
        });
        // 2.添加回调函数
        oneFuture.whenComplete((res, cause) -> {
            if (cause == null) {
                System.out.println(res);
            } else {
                System.out.println(((Throwable) cause).getLocalizedMessage());
            }
        });
        // 3.挂起当前线程， 等待异步任务，等待异步任务执行完毕
        Thread.currentThread().join();
    }

    /* ======================== 03.多个CompletableFuture进行组合运算 =========================== */

    /**
     * 第一个异步任务
     *
     * @param encodedCompanyId
     * @return
     */
    private CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
        return CompletableFuture.supplyAsync(() -> {
            // 模拟计算
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("doSomethingOne is running, id=" + encodedCompanyId);
            // 返回结果
            String id = encodedCompanyId;
            return id;
        });
    }

    /**
     * 第二个异步任务
     *
     * @param companyId
     * @return
     */
    private CompletableFuture<String> doSomethingTwo(String companyId) {
        return CompletableFuture.supplyAsync(() -> {
            // 2.1 休眠3秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("doSomethingTwo is running!!!");
            // 2.2 查询公司信息，装换为str，并返回
            String str = companyId + "alibaba";
            return str;
        });
    }

    /**
     * thenCompose:当一个Future执行完，执行另一个CompletableFuture
     * 跟thenApply()区别：thenCompose()需要返回一个CompletableFuture，thenApply()返回正常类型
     */
    @Test
    public void demo09() throws ExecutionException, InterruptedException {
        // 1.等异步任务一，执行完毕后，接着执行异步任务二
        CompletableFuture<String> result = this.doSomethingOne("123")
                .thenCompose(id -> this.doSomethingTwo(id));
        // 阻塞等待任务全部执行完
        System.out.println(result.get());
    }

    /**
     * thenCombine(): 当两个并发运行的CompletableFuture任务都完成了，使用两者的结果作为参数再执行一个异步任务
     * 跟thenCompose()区别：thenCompose，使用前一个future计算结果构建一个新的任务future，串行 1->2；
     * thenCombine，直接传入一个CompletableFuture，组合两个，构建第三个异步任务，第一、二个是并发 1 + 2 -> 3
     */
    @Test
    public void demo10() throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = this.doSomethingOne("456")
                .thenCombine(this.doSomethingTwo("789"),
                        (one, two) -> one + "  " + two);
        System.out.println(result.get());
    }

    /**
     * allOf():等待多个并发运行的CompletableFuture任务执行完毕再执行
     */
    @Test
    public void demo11() throws ExecutionException, InterruptedException {
        // 1.创建future列表
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(this.doSomethingOne("1"));
        futureList.add(this.doSomethingOne("2"));
        futureList.add(this.doSomethingOne("3"));
        futureList.add(this.doSomethingOne("4"));

        // 2.转换多个future为一个
        CompletableFuture<Void> result = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));

        // 3.等待所有的future都完成
        System.out.println(result.get());
    }

    /**
     * anyOf():多个任务，任意一个执行完毕 竞争关系
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void demo12() throws ExecutionException, InterruptedException {
        // 1.创建future列表
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(this.doSomethingOne("5"));
        futureList.add(this.doSomethingOne("6"));
        futureList.add(this.doSomethingOne("7"));
        futureList.add(this.doSomethingOne("8"));

        // 2.将多个转成一个
        CompletableFuture<Object> result = CompletableFuture.anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));

        // 3.等待执行完毕
        System.out.println(result.get());
    }

    /* ==================================== 04.异常处理 ======================================= */

    /**
     * 测试CompletableFuture任务中出现异常。get一直阻塞
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void demo13() throws ExecutionException, InterruptedException {
        // 1.创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<>();

        // 2.开启线程计算任务结果，并设置
        new Thread(() -> {
            // 2.1 休眠3秒， 模拟任务计算
            try {
                // 抛出异常
                if (true) {
                    throw new RuntimeException("Exception test");
                }
                // 设置正常结果
                future.complete("ok");
            } catch (Exception e) {
                // 如果此处不设置完成异常，下面调用get() 会一直阻塞。
                future.completeExceptionally(e);
            }
            System.out.println("----" + Thread.currentThread().getName() + "  Set future result");
        }, "thread-1").start();

        // 3.等待计算结果， 如果上面异常时不使用future.completeExceptionally设置异常 出现异常此处会一直阻塞
//        System.out.println(future.get());
        // 设置出现异常时默认值
        System.out.println(future.exceptionally(t -> "default").get());
    }
}
