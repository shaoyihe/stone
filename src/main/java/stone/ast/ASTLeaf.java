package stone.ast;

import stone.Environment;
import stone.Token;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 抽象语法树叶子节点
 * Created by heshaoyi on 8/2/17.
 */
public class ASTLeaf extends ASTree {
    private static List<ASTree> EMPTY = Collections.emptyList();

    private Token token;

    public ASTLeaf(Token token) {
        this.token = token;
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public ASTree child(int pos) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<ASTree> children() {
        return EMPTY.iterator();
    }

    @Override
    public String location() {
        return token.toString();
    }

    @Override
    public Object eval(Environment environment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Token getToken() {
        return token;
    }

}
