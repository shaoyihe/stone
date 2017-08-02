package stone.ast;

import java.util.Iterator;

/**
 *
 * Created by heshaoyi on 8/2/17.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract int numberOfChild();

    public abstract ASTree child(int pos);

    public abstract Iterator<ASTree> children();

    public abstract String location();

    @Override
    public Iterator<ASTree> iterator() {
        return children();
    }

}
