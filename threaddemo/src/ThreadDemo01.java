import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName ThreadDemo01
 * @create 2019-10-30 19:02
 * @Description 手写自旋锁
 */
public class ThreadDemo01 {
    //自定义原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    //
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in O(∩_∩)O");
        while (!atomicReference.compareAndSet(null,thread)){}
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t out");
    }

    public static void main(String[] args) {
        ThreadDemo01 threadDemo01 = new ThreadDemo01();
        new Thread(() -> {
            threadDemo01.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadDemo01.myUnLock();
        },"aa").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            threadDemo01.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadDemo01.myUnLock();
        },"bb").start();


    }
}
