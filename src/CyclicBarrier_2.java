import java.util.concurrent.*;

public class CyclicBarrier_2 {
    private static BlockingQueue<Double> result =
            new LinkedBlockingQueue<>();

    // (432*3) + (3^14) + (45*127/12) = ?
    public static void main(String[] args) {
        Runnable finish = () -> {
            System.out.println("Somando tudo.");
            double finishResult = 0;
            finishResult += result.poll();
            finishResult += result.poll();
            finishResult += result.poll();
            System.out.println("Processamento finalizado. "
                    + "Resultado final: " + finishResult
            );
        };
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, finish);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable r1 = () -> {
//      System.out.println(Thread.currentThread().getName());
            result.add(432d * 3d);
            await(cyclicBarrier);
//      System.out.println(Thread.currentThread().getName());
        };
        Runnable r2 = () -> {
//      System.out.println(Thread.currentThread().getName());
            result.add(Math.pow(3d, 14d));
            await(cyclicBarrier);
//      System.out.println(Thread.currentThread().getName());
        };
        Runnable r3 = () -> {
//      System.out.println(Thread.currentThread().getName());
            result.add(45d * 127d / 12d);
            await(cyclicBarrier);
//      System.out.println(Thread.currentThread().getName());
        };

        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);

        executor.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
