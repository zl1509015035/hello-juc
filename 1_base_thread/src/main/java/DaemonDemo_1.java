import java.util.concurrent.TimeUnit;

/**
 * @author zhul
 * @create 2022/10/17 13:20
 */
public class DaemonDemo_1 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t开始运行，" +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {

            }
        }, "t1");
//        thread.setDaemon(true);
        thread.start();

        //暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ----end 主线程");

    }
}
