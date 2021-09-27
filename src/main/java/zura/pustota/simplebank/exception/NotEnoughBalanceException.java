package zura.pustota.simplebank.exception;

public class NotEnoughBalanceException extends IllegalArgumentException {
    public NotEnoughBalanceException() {
    }

    public NotEnoughBalanceException(String s) {
        super(s);
    }

    public NotEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughBalanceException(Throwable cause) {
        super(cause);
    }
}
