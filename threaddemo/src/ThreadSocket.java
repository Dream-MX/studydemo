
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName ThreadSocket
 * @create 2019-10-30 21:01
 * @Description 生产者消费者 使用队列，原子引用
 */
public class ThreadSocket {
    public static void main(String[] args) {

        MyTest myTest = new MyTest(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            try {
                myTest.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"aa").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            try {
                myTest.myCons();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"bb").start();

        try {
            TimeUnit.SECONDS.sleep(6);
            myTest.setFLAG(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyTest{

    private BlockingQueue<String>blockingQueue=null;
    private  boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyTest(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
        String data=null;
        boolean resoult;
        while (FLAG){
           data= atomicInteger.incrementAndGet()+"";
           resoult = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (resoult) {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");

            }
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("停产了");
    }


    public void myCons() throws Exception {
        String resoult;
        while (FLAG){
           resoult = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (resoult==null || resoult.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 消费失败，库存已空");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费成功");
        }

    }

    public void setFLAG(boolean b){
        this.FLAG=b;
    }
}