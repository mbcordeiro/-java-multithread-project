import java.lang.Thread.State;

public class Volatile2 {
    private static volatile int number;
    private static volatile boolean prepared;
//  private static int number = 0;
//  private static boolean prepared = false;

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (!prepared) {
                Thread.yield();
            }

            if (number != 42) {
//        System.out.println(number);
                throw new IllegalStateException("Exception");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread t0 = new Thread(new MyRunnable());
            t0.start();
            Thread t1 = new Thread(new MyRunnable());
            t1.start();
            Thread t2 = new Thread(new MyRunnable());
            t2.start();

            number = 42;
            prepared = true;

            while (
                    t0.getState() != State.TERMINATED
                            || t1.getState() != State.TERMINATED
                            || t2.getState() != State.TERMINATED
            ) {
                // espera
            }

            number = 0;
            prepared = false;
        }
    }
}
