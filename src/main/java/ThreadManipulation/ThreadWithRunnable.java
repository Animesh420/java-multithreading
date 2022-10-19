package ThreadManipulation;

class Runner1 implements  Runnable{


    @Override
    public void run() {
        for (int i=0;i< 10;++i) {
            System.out.println("ThreadManipulation.Runner1: " + i);
        }
    }
}
class Runner2  implements  Runnable{
    @Override
    public void run() {
        for (int i=0;i< 10;++i) {
            System.out.println("ThreadManipulation.Runner2: " + i);
        }
    }
}
public class ThreadWithRunnable {
    public static void main(String[] args) {

        // multi threading for running objects of ThreadManipulation.Runner1 and ThreadManipulation.Runner2
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < 10; ++i) {
                    System.out.println("Runner3: " + i);
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
