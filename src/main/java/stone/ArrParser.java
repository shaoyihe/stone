package stone;

import stone.ast.ArrayLiteral;
import stone.ast.ArrayRef;

import static stone.Parser.rule;

/**
 * Created by heshaoyi on 8/10/17.
 */
public class ArrParser extends ClassParser {
    Parser elements = rule(ArrayLiteral.class)
            .ast(expr).repeat(rule().sep(",").ast(expr));

    public ArrParser() {
        super();
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    }
}
