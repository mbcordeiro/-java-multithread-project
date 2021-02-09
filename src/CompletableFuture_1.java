import java.util.concurrent.CompletableFuture;

public class CompletableFuture_1 {
    public static void main(String[] args) {
        CompletableFuture<String> processe = processe();

        CompletableFuture<String> thenApply =
                processe.thenApply(s -> s + " teste");

        CompletableFuture<Void> thenAccept =
                thenApply.thenAccept(s -> System.out.println(s));

        System.out.println("Executar");

        sleep();
        sleep();
        sleep();
    }

    private static CompletableFuture<String> processe() {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return "Teste !";
        });
    }

    private static final void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
