import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicClasses {
    //  static AtomicLong i = new AtomicLong(-1);
    //  static AtomicInteger i = new AtomicInteger(-1);
    //  static AtomicBoolean b = new AtomicBoolean(false);
    static AtomicReference<Object> r = new AtomicReference<>(new Object());

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t0.start();
        t1.start();
        t2.start();
    }

    public static class MYRunnable implements Runnable {
        @Override
        public void run() {

            String name = Thread.currentThread().getName();
            // System.out.println(name + ":" + i.incrementAndGet());
            // System.out.println(name + ":" + b.compareAndExchange(false, true));
            System.out.println(name + ":" + r.getAndSet(new Object()));
        }
    }
}
