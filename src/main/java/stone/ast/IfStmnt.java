package stone.ast;

import stone.Environment;

import java.util.List;

public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c) {
        super(c);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree thenBlock() {
        return child(1);
    }

    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    public String toString() {
        return "(if " + condition() + " " + thenBlock()
                + " else " + elseBlock() + ")";
    }

    @Override
    public Object eval(Environment environment) {
        Object judge = condition().eval(environment);
        //非0判断即为真
        if ((judge instanceof Boolean && judge == Boolean.FALSE) ||
                (judge instanceof Long && 0 == (Long) judge)) {
            return elseBlock().eval(environment);
        } else {
            return thenBlock().eval(environment);
        }
    }
}
