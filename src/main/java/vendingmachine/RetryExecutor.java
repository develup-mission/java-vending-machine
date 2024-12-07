package vendingmachine;

import java.util.function.Supplier;

public class RetryExecutor {

    public static <T> T executeWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
