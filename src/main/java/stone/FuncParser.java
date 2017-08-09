package stone;


import stone.ast.Args;
import stone.ast.DefStmnt;
import stone.ast.Parameters;

import static stone.Parser.rule;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class FuncParser extends BasicParser {
    Parser param = rule().identifier(reserved);
    Parser params = rule(Parameters.class).ast(param).repeat(rule().sep(",").ast(param));
    protected Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser def = rule(DefStmnt.class).sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = rule(Args.class).ast(expr).repeat(rule().sep(",").ast(expr));
    protected Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        super();
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }


}
