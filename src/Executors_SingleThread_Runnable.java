import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executors_SingleThread_Runnable {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            //executor.execute(new Assignment());
            //executor.execute(new Assignment());
            //executor.execute(new Assignment());

            Future future = executor.submit(new Assignment());
            System.out.println(future.isDone());
            executor.awaitTermination(5, TimeUnit.SECONDS);

            //      executor.shutdown();
            //      executor.awaitTermination(10, TimeUnit.SECONDS);
            //      System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null)
                executor.shutdown();
//                executor.shutdownNow();
        }
    }

    public static class Assignment implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Executed!");
        }
    }
}
