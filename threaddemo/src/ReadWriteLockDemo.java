import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName ReadWriteLockDemo
 * @create 2019-10-30 19:41
 * @Description 读写锁
 */
public class ReadWriteLockDemo {

    private volatile Map<String,Object>map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 存数据开始，key = "+key);

           // TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 数据存入成功 key = "+key);
        } catch (Exception e) {
          e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 获取数据 key = "+ key);
            System.out.println(map.get(key));
        } catch (Exception e) {
          e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        for (int i = 1; i < 6; i++) {
            final int num = i;
            new Thread(() -> {
                readWriteLockDemo.put(num+"","aa");
            },String.valueOf(i)).start();

        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < 6; i++) {
            final int num = i;
            new Thread(() -> {
              readWriteLockDemo.get(num+"");
            },String.valueOf(i)).start();

        }
    }
}
