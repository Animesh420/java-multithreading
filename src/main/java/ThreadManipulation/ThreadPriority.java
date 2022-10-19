package ThreadManipulation;

class W implements  Runnable {
    @Override
    public void run() {
        System.out.println("My priority is " + Thread.currentThread().getPriority());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}


public class ThreadPriority {
    public static void main(String[] args) {

        // a high priority thread
        System.out.println("In main, my priority is " + Thread.currentThread().getPriority());
        Thread t = new Thread(new W());
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
        System.out.println("In main, my priority is " + Thread.currentThread().getPriority());
        System.out.println("This is the main thread");
    }
}
