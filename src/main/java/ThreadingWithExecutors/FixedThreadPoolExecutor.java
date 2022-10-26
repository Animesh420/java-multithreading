package ThreadingWithExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskFixedThreadPool implements Runnable {
    private int id;

    public TaskFixedThreadPool(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("The task with id " + id +  " is in work - thread id :" + Thread.currentThread().getId());
        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}


public class FixedThreadPoolExecutor {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try{
            for (int i=0; i< 100; i++) {
                executorService.execute(new TaskFixedThreadPool(i + 1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    // executorService.shutdownNow();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
