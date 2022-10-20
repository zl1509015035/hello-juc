package CompletableFuture异步编程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zhul
 * @create 2022/10/20 17:47
 */
public class CompletableFutureAPIDemo {


    public static void main(String[] args) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        /*
          1、获得结果和出发计算
         */
//        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.get(2,TimeUnit.SECONDS));
//        System.out.println(completableFuture.join());
//        System.out.println(completableFuture.getNow("xxx"));
//        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.join());
    }


}
