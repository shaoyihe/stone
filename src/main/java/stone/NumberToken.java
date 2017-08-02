package stone;

/**
 * 数字Token
 * Created by heshaoyi on 7/31/17.
 */
public class NumberToken extends Token {

    private long number;

    public NumberToken(long number, int columnNum, int lineNum) {
        super(columnNum, lineNum);
        this.number = number;
    }


    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String getText() {
        return String.valueOf(number);
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "number=" + number + " " + super.toString() +
                '}';
    }
}
