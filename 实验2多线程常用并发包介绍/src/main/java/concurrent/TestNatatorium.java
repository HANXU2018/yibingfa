package concurrent;

import java.awt.image.VolatileImage;
import java.util.NavigableMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 19:12
 */
public class TestNatatorium {
    public static void main(String[] args) {
        Natatorium natatorium = new Natatorium();
        Thread nataThread = new Thread(natatorium);
        nataThread.start();
        natatorium.addSwimmer("zs",1);
        natatorium.addSwimmer("ls",2);
        natatorium.addSwimmer("ww",3);
    }
}
class Natatorium implements Runnable{

    private DelayQueue<Swimmer> queue = new DelayQueue<>();

    private volatile boolean isOpen = true;
    public void addSwimmer(String name, int playTime){
        long endTime = System.currentTimeMillis() + playTime * 10000 * 60;
        Swimmer swimmer = new Swimmer(name, endTime);
        System.out.println(swimmer.getName() + "进入游泳馆， 可用游泳时间"+ playTime + "分");
        this.queue.add(swimmer);
    }

    @Override
    public void run() {
        while (isOpen){
            try {
                Swimmer swimmer = queue.take();
                System.out.println(swimmer.getName() + "游泳时间结束");
                if(queue.size() == 0){
                    isOpen = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Swimmer implements Delayed{
    private String name;
    private long endTime;

    public Swimmer(String name, long endTime) {
        this.name = name;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed delayed) {
        Swimmer swimmer = (Swimmer) delayed;
        return this.getDelay(TimeUnit.SECONDS) - swimmer.getDelay(TimeUnit.SECONDS) > 0 ? 1: 0;
    }
}
