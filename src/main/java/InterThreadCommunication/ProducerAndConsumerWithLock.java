package InterThreadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws  InterruptedException {
        lock.lock();
        System.out.println("Producer method");
        condition.await();
        System.out.println("Again the producer method");
    }

    public void consume() throws  InterruptedException {

        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer method ...");
        Thread.sleep(3000);
        condition.signal();
        lock.unlock();

    }
}




public class ProducerAndConsumerWithLock {


    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    worker.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    worker.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
