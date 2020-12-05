public class Synchronized_1 {
    static int i = -1;

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        //    for (int i = 0; i < 5; i++) {
        //      runnable.run();
        //    }

        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        Thread t3 = new Thread(myRunnable);
        Thread t4 = new Thread(myRunnable);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    //  public static void print() {
    //    synchronized (Synchronized_1.class) {
    //      i++;
    //      String name = Thread.currentThread().getName();
    //      System.out.println(name + ":" + i);
    //    }
    //  }

    public static class MYRunnable implements Runnable {
        //    static Object lock1 = new Object();
        //    static Object lock2 = new Object();
        @Override
        public synchronized void run() {
            //    public void run() {
            //      print();
            //      synchronized (this) {
            i++;
            String name = Thread.currentThread().getName();
            System.out.println(name + ":" + i);
//      }
    //      synchronized(lock1) {
    //        i++;
    //        String name = Thread.currentThread().getName();
    //        System.out.println(name + ":" + i);
    //      }
    //      synchronized(lock2) {
    //        i++;
    //        String name = Thread.currentThread().getName();
    //        System.out.println(name + ":" + i);
    //      }
        }
    }
}
