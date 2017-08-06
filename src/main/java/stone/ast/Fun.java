package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * 闭包声明
 * Created by heshaoyi on 8/6/17.
 */
public class Fun extends ASTList {
    public Fun(List<ASTree> children) {
        super(children);
    }

    public Parameters params() {
        return (Parameters) ((ASTree) child(0)).child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(1);
    }

    @Override
    public Object eval(Environment environment) {
        return new Func(params(), body(), environment);
    }

    @Override
    public String toString() {
        return "Fun{} params ->" + params() + " body ->" + body();
    }
}
