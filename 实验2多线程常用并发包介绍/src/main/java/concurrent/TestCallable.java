package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 19:51
 */
public class TestCallable {
    public static void main(String[] args) {
        MyCallableThread myCallableThread = new MyCallableThread();
        FutureTask<Integer> result = new FutureTask<>(myCallableThread);

        new Thread(result).start();

        try{
            Integer sum = null;
            sum = result.get();
            System.out.println(sum);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class MyCallableThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("线程 运行中 ");
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum +=i;
            Thread.sleep(100);
        }
        return sum;
    }
}
