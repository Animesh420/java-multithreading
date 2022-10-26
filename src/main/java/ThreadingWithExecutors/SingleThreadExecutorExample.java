package ThreadingWithExecutors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("The task with id " + id +  " is in work - thread id :" + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}



public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        // it is a single thread that will the tasks sequentially
        // Single thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            for(int i=0; i < 5; i++) {
                executor.execute(new Task(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           executor.shutdown();
        }

    // output: All tasks are run sequentially by a single thread (check the thread id)
        /**
         * The task with id 0 is in work - thread id :pool-1-thread-1
         * The task with id 1 is in work - thread id :pool-1-thread-1
         * The task with id 2 is in work - thread id :pool-1-thread-1
         * The task with id 3 is in work - thread id :pool-1-thread-1
         * The task with id 4 is in work - thread id :pool-1-thread-1
          */

    }
}
