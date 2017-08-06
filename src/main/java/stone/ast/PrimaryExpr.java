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
        Object target = null;
        for (int pos = 0; pos < numChildren(); ++pos) {
            target = eval(environment, pos, target);
        }
        return target;
    }

    private Object eval(Environment env, int pos, Object target) {
        if (pos == 0) {
            return child(pos).eval(env);
        }
        return ((Postfix) child(pos)).eval(env, target);
    }
}
