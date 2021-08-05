/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 11:14
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        CarStock carStock = new CarStock();
        CarProducter2 producer = new CarProducter2(carStock);
        CarConsumer2 carConsumer = new CarConsumer2(carStock);

        Thread tProduct1 = new Thread(producer);
        Thread tProduct2 = new Thread(producer);

        Thread tConsumer1 = new Thread(carConsumer);
        Thread tConsumer2 = new Thread(carConsumer);

        tProduct1.setName("销售1🦄");
        tProduct2.setName("销售2🐥");

        tProduct1.start();
        tProduct2.start();

        tConsumer1.setName("顾客1🦀");
        tConsumer2.setName("顾客2🐳");

        tConsumer1.start();
        tConsumer2.start();


    }
}
class CarStock{
    int cars;

    public synchronized void productCar(){
        try {
            if(cars<20){
                System.out.println(Thread.currentThread().getName()+"生产🚗"+cars);
                Thread.currentThread().sleep(100);
                cars++;
                notifyAll();
            }else{
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public synchronized void ConsumeCar(){
        try{
            if(cars > 0){
                System.out.println(Thread.currentThread().getName()+"购买 🌝"+cars);
                Thread.currentThread().sleep(100);
                cars--;
                notifyAll();
            }else{
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class CarProducter2 implements Runnable{
    CarStock carStock;
    public CarProducter2(CarStock clerk){
        this.carStock = clerk;
    }

    @Override
    public void run(){
        while (true){
            carStock.productCar();
        }
    }
}

class CarConsumer2 implements Runnable{
    CarStock carStock;

    public CarConsumer2(CarStock carStock) {
        this.carStock = carStock;
    }

    @Override
    public void run() {
        while (true){
            carStock.ConsumeCar();
        }
    }
}
