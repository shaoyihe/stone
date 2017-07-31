package stone;

/**
 * 标记符（变量，操作符，关键字等）Token
 * Created by heshaoyi on 7/31/17.
 */
public class IDToken extends Token {

    private String text;

    public IDToken(String text, int columnNum, int lineNum) {
        super(columnNum, lineNum);
        this.text = text;
    }


    @Override
    public boolean isIdentity() {
        return true;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "IDToken{" +
                "str=" + text + super.toString() +
                '}';
    }
}
