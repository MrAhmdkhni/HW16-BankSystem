package exception;

public class BalanceNotEnough extends RuntimeException {
    public BalanceNotEnough(String message) {
        super(message);
    }
}
