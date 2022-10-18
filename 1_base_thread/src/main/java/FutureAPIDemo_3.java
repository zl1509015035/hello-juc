import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhul
 * @create 2022/10/18 10:02
 *
 *
 *
 * 1、get容易导致阻塞，一般建议放在程序后面，一旦调用不见不散，必须等到结果后才离开，不管是否计算完成，容易程序堵塞
 * 2、假如我不愿意等待很长时间，我希望过时不候，可以自动离开
 */
public class FutureAPIDemo_3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName()+"\t ----- come in");
            //暂停几秒钟线程
            try{TimeUnit.SECONDS.sleep(5);}catch (InterruptedException e){e.printStackTrace();}

            return "task over";
        } );

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName()+"\t -----忙其他任务了");

        /**
         * 使用get
         */
//        System.out.println(futureTask.get(2,TimeUnit.SECONDS));
//        System.out.println(futureTask.get());

        /**
         * 自旋
         */
        while(true){
            if(futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else{
                try{TimeUnit.MILLISECONDS.sleep(500);}catch (InterruptedException e){e.printStackTrace();}
                System.out.println("正在处理中，不要再催了");
            }
        }
    }
}
