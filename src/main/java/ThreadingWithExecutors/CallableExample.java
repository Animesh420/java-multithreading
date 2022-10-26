package ThreadingWithExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
       Thread.sleep(3000);
       return "ID :" + id;
    }
}



public class CallableExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();
        try {
            for (int i=0; i < 10; ++i) {
                 Future<String> f = executorService.submit(new Processor(i+1));
                 list.add(f);
            }

            for (Future<String> f: list) {
                System.out.println("Result ====> " + f.get());
            }
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            executorService.shutdown();
        }

    }
}
