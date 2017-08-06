package stone.ast;

import stone.Environment;

import java.util.List;

public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> c) {
        super(c);
    }

    public static ASTree create(List<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }

    @Override
    public Object eval(Environment environment) {
        Object result = null;
        for (ASTree asTree : this) {
            result = asTree.eval(environment);
        }
        return result;
    }
}
