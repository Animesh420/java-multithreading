package ThreadManipulation;

class RunnerJoin1 implements  Runnable{


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i=0;i< 10;++i) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadManipulation.Runner1: " + i);
        }
    }
}
class RunnerJoin2  implements  Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i=0;i< 10;++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadManipulation.Runner2: " + i);
        }
    }
}
public class ThreadWithJoins {
    public static void main(String[] args) {

        // multi threading for running objects of ThreadManipulation.Runner1 and ThreadManipulation.Runner2
        Thread t1 = new Thread(new RunnerJoin1());
        Thread t2 = new Thread(new RunnerJoin2());

        t1.start();
        t2.start();

        try {
            t1.join();
            // java waits for t1 to die (i.e. run method to completely exhaust)
            t2.join();
            // java waits for t2 to die (i.e. run method to completely exhaust)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // with the join this is printed towards the end
        System.out.println(Thread.currentThread().getName());
        System.out.println("Finished executing both threads");
    }
}
