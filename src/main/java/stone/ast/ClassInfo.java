package stone.ast;

import stone.Environment;
import stone.ParseException;

/**
 * Created by heshaoyi on 8/9/17.
 */
public class ClassInfo {
    private ClassDef classDef;
    private Environment environment;
    private ClassInfo superClass;

    public ClassInfo(ClassDef classDef, Environment environment) {
        String superClassName = classDef.superClassName();
        if (superClassName != null) {
            Object superClass = environment.get(superClassName);
            if (superClass instanceof ClassInfo) {
                this.superClass = (ClassInfo) superClass;
            } else {
                throw new ParseException(" illegal super class " + superClassName + " with object " + superClass);
            }
        }
        this.classDef = classDef;
        this.environment = environment;
    }
}
