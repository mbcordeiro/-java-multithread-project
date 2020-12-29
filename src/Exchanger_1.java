
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class Exchanger_1 {
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " toma isso");
            String msg = "Toma isso!";
            String returnExg = exchange(msg);
            System.out.println(name + " - " + returnExg);
        };
        Runnable r2 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " obrigado");
            String msg = "Obrigado!";
            String returnExg = exchange(msg);
            System.out.println(name + " - " + returnExg);
        };

        executor.execute(r1);
        executor.execute(r2);

        executor.shutdown();
    }

    private static String exchange(String msg) {
        try {
            return EXCHANGER.exchange(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Exceção";
        }
    }
}
