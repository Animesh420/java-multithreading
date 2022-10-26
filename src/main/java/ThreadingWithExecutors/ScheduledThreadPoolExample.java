package ThreadingWithExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable {
    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from the web ...");
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}


public class ScheduledThreadPoolExample {
    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//        try {
            executorService.scheduleAtFixedRate(new StockMarketUpdater(), 1000, 5000, TimeUnit.MILLISECONDS);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            executorService.shutdown();
//        }
    }
}
