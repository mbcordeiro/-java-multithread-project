import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore_2 {
    private static final Semaphore SEMAPHORE = new Semaphore(100);

    private static final AtomicInteger QTD = new AtomicInteger(0);

    public static void main(String[] args) {
        ScheduledExecutorService executor =
                Executors.newScheduledThreadPool(501);

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            int user = new Random().nextInt(10000);

            boolean ok = false;
            QTD.incrementAndGet();
            while (!ok) {
                ok = tryAcquire();
            }
            QTD.decrementAndGet();

            System.out.println("Usuário " + user
                    + " thread " + name + "\n");
            sleep();
            SEMAPHORE.release();
        };

        Windows.Mensagem windows = Windows.createWindow("QTD");
        Runnable r2 = () -> {
            int qtd = QTD.get();
            windows.setText(qtd + ".");
        };

        for (int i = 0; i < 500; i++) {
            executor.execute(r1);
        }

        executor.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);
    }

    private static void sleep() {
        // espera de 1 a 6 segundos
        try {
            int waitingTime = new Random().nextInt(6);
            waitingTime++;
            Thread.sleep(1000 * waitingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static boolean tryAcquire() {
        try {
            return SEMAPHORE.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return false;
        }
    }
}
