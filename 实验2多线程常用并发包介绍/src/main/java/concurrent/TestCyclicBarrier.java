package concurrent;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 19:39
 */
public class TestCyclicBarrier {
    static class MyThread implements Runnable{
        private CyclicBarrier barrier;
        private String name;

        public MyThread(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int)(10000*Math.random()));
                System.out.println(this.name + "到场");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(this.name + "开始会议");
        }
    }

    public static void main(String[] args) throws IOException,InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(3);

        CyclicBarrier barrier = new CyclicBarrier(3);

        executors.submit(new MyThread(barrier,"zs"));
        executors.submit(new MyThread(barrier,"ls"));
        executors.submit(new MyThread(barrier,"ww"));

    }
}
