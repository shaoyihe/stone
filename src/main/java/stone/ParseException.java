package stone;

/**
 * Created by heshaoyi on 7/31/17.
 */
public class ParseException extends RuntimeException {
    public ParseException(String s, Token token) {
        super(s + "" + token.toString());
    }

    public ParseException(Token t) {
        super(t.toString());
    }

    public ParseException(String m) {
        super(m);
    }
}
