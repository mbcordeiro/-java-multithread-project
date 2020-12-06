import java.util.Random;
import java.util.concurrent.*;

public class Executors_SingleThread_Callable {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();

            Future<String> future = executor.submit(new Assignment());

            System.out.println(future.isDone());
//      System.out.println(future.get());
            System.out.println(future.get(1, TimeUnit.SECONDS));
            System.out.println(future.isDone());

//      executor.shutdown();
//      executor.awaitTermination(10, TimeUnit.SECONDS);
//      System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
//        executor.shutdown();
                executor.shutdownNow();
            }
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
