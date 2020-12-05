import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class CompetitionCollections {
//    private static List<String> list = new CopyOnWriteArrayList<>();
//    private static Map<Integer, String> map = new ConcurrentHashMap<>();
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        // Coleções que são Thread-safe

        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(queue);
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            //list.add("Executed");
            //      map.put(new Random().nextInt(), "Executed!");
            queue.add("Executed!");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inserted in the queue!");
        }
    }
}
