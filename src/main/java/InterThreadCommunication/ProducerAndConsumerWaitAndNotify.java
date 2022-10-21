package InterThreadCommunication;

import java.util.ArrayList;
import java.util.List;

class Process{

    public void produce() throws  InterruptedException {

        synchronized (this) {
            System.out.println("Running the produce method....");
            wait();
            System.out.println("Again in the produce method");
        }

    }

    public void consume() throws  InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Running the consume method....");
            notify();
            Thread.sleep(5000);
        }
    }
}

class Processor {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private  final Object lock = new Object();
    private int value = 0;

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items ...");
                    lock.wait();
                    // Leaves execution for consumer thread
                }
                else {
                    System.out.println("Adding a new item with value " + value);
                    list.add(value);
                    value ++;
                    lock.notify();
                    // notify does not finish until all the code in synchronized block is executed
                    // in this example, synchronized block is a while loop
                    // so notify will happen until list.size() == UPPER_LIMIT and then other thread can execute
                }

                Thread.sleep(500);
            }
        }
    }

    public void consume() throws  InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items ...");
                    value = 0;
                    lock.wait();
                    // Leaves execution for producer thread
                }
                else {
                    System.out.println("Popping a new item with value " + list.remove(list.size() - 1));
                    lock.notify();
                    // notify does not finish until all the code in synchronized block is executed
                    // in this example, synchronized block is a while loop
                    // so notify will happen until list.size() == LOWER_LIMIT and then other thread can execute
                }

                Thread.sleep(500);
            }
        }
    }
}

public class ProducerAndConsumerWaitAndNotify {


    public static void main(String[] args) {
//        Process process = new Process();
        Processor process = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        t1.start();
        t2.start();
    }


}
