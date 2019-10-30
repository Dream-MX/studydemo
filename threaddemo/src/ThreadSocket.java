import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName ThreadSocket
 * @create 2019-10-30 21:01
 * @Description TODO
 */
public class ThreadSocket {
}
class MyTest{
    private BlockingDeque<String> blockingDeque=null;
    private final boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyTest(BlockingDeque<String> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    public void myProd() throws Exception {
        String data=null;
        boolean resoult;
        while (FLAG){
           data= atomicInteger.incrementAndGet()+"";
           resoult = blockingDeque.offer(data, 2L, TimeUnit.SECONDS);
        }
    }
}