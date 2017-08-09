package stone;

import stone.ast.ClassBody;
import stone.ast.ClassDef;
import stone.ast.Dot;

import static stone.Parser.rule;

/**
 * Created by heshaoyi on 8/9/17.
 */
public class ClassParser extends LocalVarParser {
    Parser member = rule().or(def, simple);

    Parser classBody = rule(ClassBody.class).sep("{").option(member)
            .repeat(rule().sep(";", Token.EOL).option(member))
            .sep("}");

    Parser classDef = rule(ClassDef.class).sep("class").identifier(reserved).option(rule().sep("extends").identifier(reserved))
            .ast(classBody);

    public ClassParser() {
        super();
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(classDef);
    }
}
