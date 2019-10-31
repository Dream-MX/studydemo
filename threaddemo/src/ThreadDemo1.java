import java.util.concurrent.*;

/**
 * @author leixiang
 * @version 1.0.0
 * @ClassName ThreadDemo1
 * @create 2019-10-31 19:12
 * @Description 线程创建方式之线程池
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理完成");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
