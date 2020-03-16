import java.util.concurrent.TimeUnit;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName MyTest01
 * @create 2019-11-01 22:45
 * @Description TODO
 */
public class MyTest01 {
    public static void main(String[] args) {


        System.out.println("=========");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {e.printStackTrace();}
    }
}
