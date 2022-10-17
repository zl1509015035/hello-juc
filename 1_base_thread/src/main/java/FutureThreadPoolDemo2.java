import java.util.concurrent.*;

/**
 * @author zhul
 * @create 2022/10/17 17:35
 */
public class FutureThreadPoolDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //3个任务，目前只有一个线程main执行 耗时多少
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(()->{
            try{TimeUnit.MILLISECONDS.sleep(500);}catch (InterruptedException e){e.printStackTrace();}
            return "task1 over ";
        });
        threadPool.submit(futureTask1);


        FutureTask<String> futureTask2 = new FutureTask<String>(()->{
            try{TimeUnit.MILLISECONDS.sleep(300);}catch (InterruptedException e){e.printStackTrace();}
            return "task2 over ";
        });
        threadPool.submit(futureTask2);

        //获取返回值
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        try{TimeUnit.MILLISECONDS.sleep(300);}catch (InterruptedException e){e.printStackTrace();}

//        System.out.println(futureTask3.get());


        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ----- end ");
        threadPool.shutdown();


    }

    private static void m1(){
        long startTime = System.currentTimeMillis();

        try{TimeUnit.MILLISECONDS.sleep(500);}catch (InterruptedException e){e.printStackTrace();}
        try{TimeUnit.MILLISECONDS.sleep(300);}catch (InterruptedException e){e.printStackTrace();}
        try{TimeUnit.MILLISECONDS.sleep(300);}catch (InterruptedException e){e.printStackTrace();}


        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ----- end ");

    }
}
