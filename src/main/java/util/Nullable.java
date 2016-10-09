package util;

/**
 * Created by Martha on 7/31/2016.
 */
public class Nullable<T> {
    private final T obj;
    private String message;
    public Nullable(T t) {
        obj = t;
    }
    public T get() {
        return obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}