package stone.ast;

import stone.Environment;

import java.util.List;

/**
 * Created by heshaoyi on 8/9/17.
 */
public class ClassDef extends ASTList {
    public ClassDef(List<ASTree> children) {
        super(children);
    }

    public String name() {
        return ((ASTLeaf) child(0)).text();
    }

    public String superClassName() {
        return numChildren() > 2 ? ((ASTLeaf) child(1)).text() : null;
    }

    public ClassBody body() {
        return (ClassBody) child(numChildren() - 1);
    }

    @Override
    public Object eval(Environment environment) {
        String name = name();
        environment.put(name, new ClassInfo(this, environment));
        return name;
    }
}
