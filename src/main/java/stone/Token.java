package stone;

/**
 * token词
 * on 2017/7/31.
 */
public abstract class Token {
    public static Token EOF = new Token(-1, -1) {
    };

    public static final String EOL = "\\n";

    protected int lineNum;
    protected int columnNum;

    public Token(int columnNum, int lineNum) {
        this.columnNum = columnNum;
        this.lineNum = lineNum;
    }

    public long getNumber() {
        throw new UnsupportedOperationException();
    }

    public String getText() {
        throw new UnsupportedOperationException();
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isIdentifier() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    @Override
    public String toString() {
        return "Token{" +
                "lineNum=" + lineNum +
                ", columnNum=" + columnNum +
                '}';
    }
}
