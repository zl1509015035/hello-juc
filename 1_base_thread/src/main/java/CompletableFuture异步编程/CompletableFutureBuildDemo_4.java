package CompletableFuture异步编程;

import java.util.concurrent.*;

/**
 * @author zhul
 * @create 2022/10/18 17:53
 */
public class CompletableFutureBuildDemo_4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

       ExecutorService threadPool = Executors.newFixedThreadPool(3);

/*         CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟
            try{
                TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}

        },threadPool);

        System.out.println(completableFuture.get());*/


        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟
            try{
                TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}
            return "hello";
        },threadPool);

        System.out.println(completableFuture.get());

        threadPool.shutdown();

    }
}
