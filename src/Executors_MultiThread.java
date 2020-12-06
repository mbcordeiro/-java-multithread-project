import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Executors_MultiThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = null;
        try {
            //executor = Executors.newFixedThreadPool(3);
            executor = Executors.newCachedThreadPool();
            List<Assignment> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new Assignment());
            }

//            String s = executor.invokeAny(list);
//            System.out.println(s);

            List<Future<String>> futures = executor.invokeAll(list);

            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
//            Future f1 = executor.submit(new Assignment());
//            System.out.println(f1.get());
//            Future f2 = executor.submit(new Assignment());
//            Future f3 = executor.submit(new Assignment());
//            System.out.println(f2.get());
//            System.out.println(f3.get());
            executor.shutdown();
        } catch (Exception e) {
            throw  e;
        } finally {
            if (executor != null)
                executor.shutdownNow();
        }

    }

    public static class Assignment implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            int nextInt = new Random().nextInt(100);
            String name = Thread.currentThread().getName();
            return name + ":Executed";
        }
    }
}
