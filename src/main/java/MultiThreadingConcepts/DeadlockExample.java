package MultiThreadingConcepts;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        DeadlockExample dead = new DeadlockExample();

        new Thread(dead::worker1, "worker1").start();
        new Thread(dead::worker2, "worker2").start();


    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker 1 gets the lock1 ...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("Worker 1 gets the lock2 ...");
        lock1.unlock();
        lock2.unlock();
    }


    public void worker2() {
        lock2.lock();
        System.out.println("Worker 2 gets the lock1 ...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println("Worker 2 gets the lock2 ...");
        lock2.unlock();
        lock1.unlock();
    }


}
