/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 10:40
 */
public class TestVolatile_1 {
    public static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        num++;
                    }
                }
            }).start();
        }
        Thread.sleep(3000);

        System.out.printf(String.valueOf(num));
    }
}
