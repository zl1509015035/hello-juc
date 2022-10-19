package CompletableFuture异步编程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhul
 * @create 2022/10/17 17:06
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();

        System.out.println(futureTask.get());

    }
}

/**
 * 有返回值
 * 会抛异常
 */
class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("-----come in call()-----");
        return "hello Callable";
    }
}
