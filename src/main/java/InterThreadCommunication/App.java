package InterThreadCommunication;

public class App {

    private static  final Object lock1 = new Object();
    private static  final Object lock2 = new Object();

    public static int counter1 = 0;
    public static int counter2 = 0;

    // method is executed only once by any thread
    public static  void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }
    public static synchronized void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                   increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                   increment2();
                }
            }
        });


        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The counter is " + counter1);
        System.out.println("The counter is " + counter2);

    }

    public static void main(String[] args) {
        process();
        }

    }



