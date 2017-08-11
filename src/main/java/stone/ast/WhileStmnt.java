package stone.ast;

import stone.Environment;

import java.util.List;

public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> c) {
        super(c);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree body() {
        return child(1);
    }

    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }

    @Override
    public Object eval(Environment environment) {
        Object result = null;
        while (true) {
            Object judge = condition().eval(environment);
            if ((judge instanceof Boolean && judge == Boolean.FALSE) ||
                    (judge instanceof Long && 0 == (Long) judge)) {
                break;
            }
            result = body().eval(environment);
        }
        return result;
    }
}
