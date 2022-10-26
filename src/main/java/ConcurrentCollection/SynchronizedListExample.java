package ConcurrentCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListExample {

    public static void main(String[] args) {
        // under the hood, add() and remove() method are synchronized
        // intrinsic lock - not that efficient because threads have to wait for each other, even when they want to execute
        // independent methods (operations)
        List<Integer> nums = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    nums.add(i);
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    nums.add(i);
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Size of array is " + nums.size());


    }
}
