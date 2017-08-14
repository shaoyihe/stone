package stone.ast;

import stone.Environment;

import java.util.Iterator;

/**
 * Created by heshaoyi on 8/2/17.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract int numChildren();

    public abstract ASTree child(int pos);

    public abstract Iterator<ASTree> children();

    public abstract String location();

    @Override
    public Iterator<ASTree> iterator() {
        return children();
    }

    /**
     * 执行变量
     *
     * @param environment
     * @return
     */
    public abstract Object eval(Environment environment);

    public void lookup(Symbols syms) {
    }

}
