import java.util.concurrent.*;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 11:32
 */
class CarData {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class CarStockQueue {
    private static int count = 0;

    private BlockingQueue<CarData> queue;

    public CarStockQueue(BlockingQueue<CarData> queue) {
        this.queue = queue;
    }

    public synchronized void productCar(){
        CarData carData = new CarData();
        try {
            boolean success = this.queue.offer(carData,2, TimeUnit.SECONDS);
            if(success){
                int id = count++;
                carData.setId(id);
                System.out.println(Thread.currentThread().getName() + "生产 car 编号"+ id + ", 库存" + queue.size());
                Thread.sleep((int)(1000 * Math.random()));
                notifyAll();
            }else{
                System.out.println("生产 car 失败🤡");
            }

            if(queue.size() < 10){

            }else {
                System.out.println("👖库存已满 等待消费");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void ConsumeCar(){
        try {
            CarData carData = this.queue.poll(2,TimeUnit.SECONDS);
            if(carData != null){
                Thread.sleep((int)(1000 * Math.random()));
                notifyAll();
                System.out.println(Thread.currentThread().getName()+ "消费 Car "+ carData.getId() + "库存"+ queue.size());
            }else{
                System.out.println("消费 Car 失败");
            }
            if(queue.size() > 0) {

            }else{
                System.out.println("库存为空 等待生产");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class CarProducter implements Runnable{
    private CarStockQueue carStock;

    private volatile boolean isRunning = true;

    public CarProducter(CarStockQueue carStock) {
        this.carStock = carStock;
    }

    @Override
    public void run() {
        while (isRunning){
            carStock.productCar();
        }
    }

    public void stop(){
        this.isRunning = false;
    }
}

class CarConsumer implements Runnable{
    private CarStockQueue carStock;
    public CarConsumer(CarStockQueue carStock){
        this.carStock = carStock;
    }
    @Override
    public void run(){
        while (true){
            carStock.ConsumeCar();
        }
    }
}

public class TestProducerAndConsumer {
    public static void main(String[] args) {
        BlockingQueue<CarData> queue = new LinkedBlockingDeque<CarData>(10);
        CarStockQueue carStockQueue = new CarStockQueue(queue);

        CarProducter carProducter1 = new CarProducter(carStockQueue);
        CarProducter carProducter2 = new CarProducter(carStockQueue);

        CarConsumer carConsumer1 = new CarConsumer(carStockQueue);
        CarConsumer carConsumer2 = new CarConsumer(carStockQueue);

        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(carProducter1);
        cachePool.execute(carProducter2);
        cachePool.execute(carConsumer1);
        cachePool.execute(carConsumer2);

    }
}
