package CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhul
 * @create 2022/10/20 21:04
 * <p>
 * 对计算结果进行消费
 * <p>
 * thenRun：
 * thenRun(Runnable runnable)
 * 任务A执行完执行B，B不需要A的结果
 * thenAccept
 * thenAccept(Consumer action)
 * 任务A执行完执行B，B需要A的结果，但是任务B 无返回值
 * thenApply
 * thenApply(Function fn)
 * 任务A执行完执行B，B需要A的结果，同时任务B有返回值
 * <p>
 * exceptionally 可捕获异常
 *
 */
public class CompletableFutureAPI3Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.shutdown();

        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(System.out::println);

        /*
        * thenRun：
        *   thenRun(Runnable runnable)
        *   任务A执行完执行B，B不需要A的结果
        */
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());
        /*
         * thenAccept
         * thenAccept(Consumer action)
         * 任务A执行完执行B，B需要A的结果，但是任务B 无返回值
         */
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(System.out::println).join());
        /*
         * thenApply
         * thenApply(Function fn)
         * 任务A执行完执行B，B需要A的结果，同时任务B有返回值
        */
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());


    }
}
