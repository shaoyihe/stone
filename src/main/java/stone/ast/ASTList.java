package stone.ast;

import java.util.Iterator;
import java.util.List;

/**
 * Created by heshaoyi on 8/2/17.
 */
public abstract class ASTList extends ASTree {
    private List<ASTree> children;

    public ASTList(List<ASTree> children) {
        this.children = children;
    }

    @Override
    public ASTree child(int pos) {
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
    public int numberOfChild() {
        return children.size();
    }
}