package concurrent;

import org.omg.CORBA.portable.IDLEntity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.jar.JarEntry;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 19:03
 */
public class TestPriorityBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<MyJob> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.add(new MyJob(3));
        priorityQueue.add(new MyJob(2));
        priorityQueue.add(new MyJob(1));

        System.out.println("队列 "+ priorityQueue);

        System.out.println("从队列中取元素" + priorityQueue.take().getId());
        System.out.println("容器： "+ priorityQueue);

    }
}
class MyJob implements Comparable<MyJob>{
    private int id;

    public MyJob(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public int compareTo(MyJob job) {
        return this.id > job.id ? 1 : (this.id < job.id ? -1 : 1);
    }
}