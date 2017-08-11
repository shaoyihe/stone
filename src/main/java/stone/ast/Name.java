package stone.ast;

import stone.Environment;
import stone.ParseException;
import stone.Token;

/**
 * 变量、操作符等字面值
 * Created by heshaoyi on 8/2/17.
 */
public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }

    public String name() {
        return getToken().getText();
    }

    @Override
    public Object eval(Environment environment) {
        Object value = environment.get(name());
        if (value == null) {
            throw new ParseException("unkown var " + name());
        }
        return value;
    }
}
