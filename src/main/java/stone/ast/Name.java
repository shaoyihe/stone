package stone.ast;

import stone.Environment;
import stone.ParseException;
import stone.Token;

/**
 * 变量、操作符等字面值
 * Created by heshaoyi on 8/2/17.
 */
public class Name extends ASTLeaf {
    static final int UNKNOWN = -1;

    private int nest;
    private int index;

    public Name(Token token) {
        super(token);
    }

    public String name() {
        return getToken().getText();
    }

    @Override
    public Object eval(Environment environment) {
        Object value = environment.get(nest, index);
        if (value == null) {
            throw new ParseException("unkown var " + name());
        }
        return value;
    }

    @Override
    public void lookup(Symbols syms) {
        Symbols.Location loc = syms.get(name());
        if (loc == null)
            throw new ParseException("undefined name: " + name());
        else {
            nest = loc.nest;
            index = loc.index;
        }
    }

    public void evalForAssign(Environment env, Object value) {
        if (index == UNKNOWN)
            env.put(name(), value);
        else
            env.put(nest, index, value);
    }

    public void lookupForAssign(Symbols syms) {
        Symbols.Location loc = syms.put(name());
        nest = loc.nest;
        index = loc.index;
    }
}
