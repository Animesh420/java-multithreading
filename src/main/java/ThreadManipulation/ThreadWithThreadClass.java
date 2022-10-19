package ThreadManipulation;

class RunnerThread1 extends  Thread {


    @Override
    public void run() {
        for (int i=0;i< 10;++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("ThreadManipulation.Runner1: " + i);
        }
    }
}
class RunnerThread2  extends   Thread{
    @Override
    public void run() {
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

public class ThreadWithThreadClass {
    public static void main(String[] args) {
        Thread t1 = new RunnerThread1();
        Thread t2 = new RunnerThread2();

        t1.start();
        t2.start();
    }
}
