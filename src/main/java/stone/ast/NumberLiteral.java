package stone.ast;

import stone.Environment;
import stone.Token;

/**
 *  数字字面值
 * Created by heshaoyi on 8/2/17.
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token token) {
        super(token);
    }

    public long value() {
        return getToken().getNumber();
    }

    @Override
    public Object eval(Environment environment) {
        return value();
    }
}
