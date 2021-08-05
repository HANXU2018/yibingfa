package java8;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 16:31
 */
public class HelloWorld {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();

        new Thread(() -> System.out.println("🥶")).start();
    }
}
