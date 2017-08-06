package stone;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class LocalVarParser extends ClosureParser {
    public LocalVarParser() {
        super();
        primary.insertChoice(Parser.rule(LocalVarExpr.class).sep("var").ast(expr));
    }
}
