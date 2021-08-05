package concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 17:34
 */
public class TestReadWriteLock {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final TestReadWriteLock test = new TestReadWriteLock();
        new Thread(()->{
            test.myWrite(Thread.currentThread());
            test.myRead(Thread.currentThread());
        },"t1").start();
        new Thread(()->{
            test.myRead(Thread.currentThread());
            test.myWrite(Thread.currentThread());
        },"t2").start();
    }
    public void myRead(Thread thread){
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 60; i++) {
                System.out.println(thread.getName() + " 读操作ing");
            }
            System.out.println(thread.getName() + "读操作完毕🤡");
        }finally {
            rwl.readLock().unlock();
        }
    }

    public void myWrite(Thread thread){
        rwl.writeLock().lock();
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println(thread.getName() + " 写操作ing");
            }
            System.out.println(thread.getName() + "写操作完毕🌚");

        }finally {
            rwl.writeLock().unlock();
        }
    }
}
