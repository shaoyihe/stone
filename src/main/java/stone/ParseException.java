package stone;

/**
 * Created by heshaoyi on 7/31/17.
 */
public class ParseException extends RuntimeException {
    public ParseException(String s, Exception e) {
        super(s, e);
    }

    public ParseException(String message) {
        super(message);
    }
}
