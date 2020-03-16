import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName LockDemo01
 * @create 2019-11-01 11:47
 * @Description 写一个线程死锁程序。 jps查询进程号，jstack 找错
 */
public class LockDemo01 {
    public static void main(String[] args) {
        String lockA="AA";
        String lockB="BB";
        new Thread(new MyLock(lockA,lockB),"AA").start();
        new Thread(new MyLock(lockB,lockA),"AA").start();
        try {TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {e.printStackTrace();}
    }

}

class MyLock implements Runnable{

    private String lockA;
    private String lockB;

    public MyLock(String lockA , String lockB){
        this.lockA=lockA;
        this.lockB=lockB;
    }

    @Override
    public void run() {
        synchronized(lockA){
            System.out.println(Thread.currentThread().getName()+"\t 拿着"+lockA+"\t 找"+lockB);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 拿着"+lockB+"\t 找"+lockA);
            }

        }
    }
}
