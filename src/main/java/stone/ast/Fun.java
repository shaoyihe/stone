package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * 闭包声明
 * Created by heshaoyi on 8/6/17.
 */
public class Fun extends ASTList {
    private int size;

    public Fun(List<ASTree> children) {
        super(children);
    }

    public Parameters params() {
        return (Parameters) child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(1);
    }

    @Override
    public Object eval(Environment environment) {
        return new OptFunction(params(), body(), environment, size);
    }

    @Override
    public String toString() {
        return "Fun{} params ->" + params() + " body ->" + body();
    }

    @Override
    public void lookup(Symbols syms) {
        size = lookup(syms, params(), body());
    }

    public static int lookup(Symbols syms, Parameters params, BlockStmnt body) {
        Symbols newSyms = new Symbols(syms);
        params.lookup(newSyms);
        body.lookup(newSyms);
        return newSyms.size();
    }
}
