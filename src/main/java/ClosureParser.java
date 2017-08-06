import stone.FuncParser;
import stone.ast.Fun;

import static stone.Parser.rule;


/**
 * Created by heshaoyi on 8/6/17.
 */
public class ClosureParser extends FuncParser {

    public ClosureParser() {
        super();
        primary.insertChoice(rule(Fun.class)
                .sep("fun").ast(paramList).ast(block));
    }


}
