package MultiThreadingConcepts;

class Worker implements Runnable {



//    private boolean terminated;
    private volatile boolean terminated;
    // volatile makes sure that the variable is always read from main memory and not from CPU cache

    @Override
    public void run() {

        while (!terminated) {
            System.out.println("Working class is running ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}


public class VolatileExample {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.setTerminated(true);
        System.out.println("Algorithm terminated ....");
    }
}
