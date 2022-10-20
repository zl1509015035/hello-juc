package CompletableFuture异步编程;

import java.util.concurrent.*;

/**
 * @author zhul
 * @create 2022/10/19 10:14
 */
public class CompletableFutureUserDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {

            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                //暂停几秒钟
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("-----1秒钟后出结果:" + result);

                //手动制作异常
                if(result>1){
                    int i=10/0;
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("------计算完成，更新系统UpdateValue：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }




        System.out.println(Thread.currentThread().getName()+"线程先去忙其他任务");




    }

    private static void future1()throws InterruptedException,ExecutionException{
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t ----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            //暂停几秒钟
            try{
                TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}
            System.out.println("-----1秒钟后出结果:"+result);
            return result;
        });

        System.out.println(Thread.currentThread().getName()+"线程先去忙其他任务");

        System.out.println(completableFuture.get());
    }


}
