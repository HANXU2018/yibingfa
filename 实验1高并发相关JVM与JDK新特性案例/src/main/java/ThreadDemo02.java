/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 10:57
 */
public class ThreadDemo02 implements Runnable {

    private Integer tickets = 100;

    @Override
    public void run() {
        while (true) {
            sellTickets();
        }
    }

    public synchronized void sellTickets() {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + tickets);
            tickets--;
        }

    }

    public static void main(String[] args) {
        ThreadDemo02 t = new ThreadDemo02();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.setName("t1 售票🎫");
        t2.setName("t2 售票🔥");
        t1.start();
        t2.start();

    }
}
