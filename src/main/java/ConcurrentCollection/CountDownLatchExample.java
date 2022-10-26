package ConcurrentCollection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements  Runnable {

    private int id;
    private CountDownLatch latch;

    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();

    }

    private void doWork() {
        try {
            System.out.println("Thread with id " + this.id + " starts working ...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for(int i=0; i < 5; ++i) {
            executorService.execute(new Worker(i, latch));
        }

        try {
            latch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks have been finished ....");
        executorService.shutdown();

    }
}
