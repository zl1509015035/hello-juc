package CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zhul
 * @create 2022/11/10 14:26
 *
 * 对计算结果速度进行选用
 */
public class CompletableFutureFastDemo {

    public static void main(String[] args) {

        CompletableFuture<String> palyA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            //暂停几秒钟
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            return "playA";
        });

        CompletableFuture<String> palyB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            //暂停几秒钟
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return "playA";
        });

        CompletableFuture<String> result = palyA.applyToEither(palyB, f -> {return f + " is winner ";});

        System.out.println(Thread.currentThread().getName()+"\t"+"------: "+result.join());

    }
}
