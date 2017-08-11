package stone;

/**
 * 字符串字面值Token
 * Created by heshaoyi on 7/31/17.
 */
public class StrToken extends Token {

    private String str;

    public StrToken(String str, int columnNum, int lineNum) {
        super(columnNum, lineNum);
        this.str = str;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String getText() {
        return str;
    }

    @Override
    public String toString() {
        return "StrToken{" +
                "str=" + str + " " + super.toString() +
                '}';
    }
}
