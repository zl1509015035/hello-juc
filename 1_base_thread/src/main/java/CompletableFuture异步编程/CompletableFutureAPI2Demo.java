package CompletableFuture异步编程;

import java.util.concurrent.*;

/**
 * @author zhul
 * @create 2022/10/20 21:04
 *
 * 对计算结果进行处理
 *
 * supply 出现异常会直接中断
 * handle 可以带着exception继续往下 后续操作可对exception进行判断操作
 *
 * exceptionally 可捕获异常
 *
 */
public class CompletableFutureAPI2Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

         ExecutorService threadPool = Executors.newFixedThreadPool(3);


         CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("111");
            return 1;
        }, threadPool).handle((f, e) -> {
//            int i = 10 / 0;
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果: " + v);
            }
        }).exceptionally(e -> {
            throw new RuntimeException("细狗异常");
        });

        System.out.println(completableFuture.get());

        System.out.println(completableFuture.join());

        System.out.println(Thread.currentThread().getName()+"----主线程先去忙其他事");

//        try{TimeUnit.SECONDS.sleep(2);}catch (InterruptedException e){e.printStackTrace();}

        threadPool.shutdown();

    }
}
