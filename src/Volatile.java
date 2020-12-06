public class Volatile {
    private static int number;
    private static boolean prepared;

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (!prepared) {
                Thread.yield();
            }

            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(new MyRunnable());
        t0.start();
        number = 42;
        prepared = true;
    }
}
