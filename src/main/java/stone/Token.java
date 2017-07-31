package stone;

/**
 * token词
 * on 2017/7/31.
 */
public abstract class Token {
    public static Token END = new Token(-1, -1) {
    };

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

    public boolean isIdentity() {
        return false;
    }

    public boolean isStr() {
        return false;
    }

    @Override
    public String toString() {
        return "Token{" +
                "columnNum=" + columnNum +
                ", lineNum=" + lineNum +
                '}';
    }
}
