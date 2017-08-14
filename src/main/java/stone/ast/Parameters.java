package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class Parameters extends ASTList {
    private int[] offsets;

    public Parameters(List<ASTree> children) {
        super(children);
    }

    public String name(int index) {
        return ((ASTLeaf) child(index)).getToken().getText();
    }

    public int size() {
        return numChildren();
    }

    public void lookup(Symbols syms) {
        int s = size();
        offsets = new int[s];
        for (int i = 0; i < s; i++)
            offsets[i] = syms.putNew(name(i));
    }

    public void eval(Environment env, int index, Object value) {
        env.put(0, offsets[index], value);
    }

}
