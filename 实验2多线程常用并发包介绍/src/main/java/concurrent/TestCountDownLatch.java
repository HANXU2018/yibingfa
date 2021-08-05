package concurrent;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 19:31
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        MyThread myThread = new MyThread(countDownLatch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(myThread).start();
        }
        try{
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+ (end - start));
    }
}
class MyThread implements Runnable{
    private CountDownLatch latch;
    public MyThread(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"启动"+latch.getCount());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
