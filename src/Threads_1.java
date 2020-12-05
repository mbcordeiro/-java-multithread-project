public class Threads_1 {
    public static void main(String[] args) {
        //Thread atual
        Thread t = Thread.currentThread();
        System.out.println(t.getName());

        MyRunnable myRunnable = new MyRunnable();

        //Nova Thread
        Thread t1 = new Thread(myRunnable);
        //t1.run(); //apenas executando na mesma thread


        // Runnable como lambda
        Thread t2 = new Thread(() -> Thread.currentThread().getName());
        // t2.start(); // NÃO FAÇA! VAI LANÇAR EXCEÇÃO!

        // Várias threads
        Thread t3 = new Thread(myRunnable);

        //Execucao
        t1.start();
        t2.start();
        t3.start();
    }
}
