import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 10:40
 */
public class TestVolatile_2 {
    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 2000; j++) {
                        num.incrementAndGet();
                    }
                }
            }).start();
        }
        Thread.sleep(10000);

        System.out.printf(num.toString());
    }
}
