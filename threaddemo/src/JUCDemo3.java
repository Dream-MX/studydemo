import javax.activation.MailcapCommandMap;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName JUCDemo3
 * @create 2019-10-31 9:12
 * @Description juc 之信号灯 Semaphore
 */
public class JUCDemo3 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到资源");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(40));
                    System.out.println(Thread.currentThread().getName()+"\t 放开资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
