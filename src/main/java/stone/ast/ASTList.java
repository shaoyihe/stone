package stone.ast;

import stone.Environment;
import stone.ParseException;

import java.util.Iterator;
import java.util.List;

/**
 * 非叶子节点描述符
 * Created by heshaoyi on 8/2/17.
 */
public class ASTList extends ASTree {
    private List<ASTree> children;

    public ASTList(List<ASTree> children) {
        this.children = children;
    }

    @Override
    public ASTree child(int pos) {
        if (pos == 1 && children.size() == 1) {
            throw new ParseException(this.getClass() + ";" + toString());
        }
        return children.get(pos);
    }

    @Override
    public Iterator<ASTree> children() {
        return children.iterator();
    }


    @Override
    public String location() {
        for (ASTree asTree : children) {
            if (asTree.location() != null) {
                return asTree.location();
            }
        }
        return null;
    }

    @Override
    public Object eval(Environment environment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sep = "";
        for (ASTree t : children) {
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }


    @Override
    public int numChildren() {
        return children.size();
    }
}
