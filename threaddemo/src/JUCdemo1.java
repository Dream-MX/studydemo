import java.util.concurrent.CountDownLatch;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName JUCdemo1
 * @create 2019-10-31 8:50
 * @Description juc测试之CountDownLatch(减少计数器)
 */
public class JUCdemo1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 号线程结束");
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("全部结束");

    }

}
