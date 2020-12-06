import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;

public class Executors_Scheduled {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

//        ScheduledFuture<String> future = executor.schedule(new Assignment(), 2, TimeUnit.SECONDS);
//        System.out.println(future.get());

//        executor.schedule(new Assignment(), 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new Assignment(), 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(new Assignment(), 0, 1, TimeUnit.SECONDS);
        executor.shutdown();

//        System.out.println(System.currentTimeMillis());
    }

    public static class Assignment implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalTime.now());
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            System.out.println(name + ": Executed! " + nextInt);
        }
    }

//    public static class Assignment implements Callable<String> {
//        @Override
//        public String call() throws Exception {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//            int nextInt = new Random().nextInt(100);
//            String name = Thread.currentThread().getName();
//            return name + ":Executed";
//        }
//    }
}
