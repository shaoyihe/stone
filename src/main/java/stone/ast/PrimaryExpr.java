package stone.ast;

import stone.BasicEnvironment;
import stone.Environment;
import stone.ParseException;

import java.util.List;

public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> c) {
        super(c);
    }

    public static ASTree create(List<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }


    @Override
    public Object eval(Environment environment) {
        Object target = null;
        for (int pos = 0; pos < numChildren(); ++pos) {
            target = eval(environment, pos, target);
        }
        return target;
    }


    /**
     * 写入对象余值
     *
     * @param environment
     * @param rVal
     * @return
     */
    public Object evalAssign(Environment environment, Object rVal) {
        Object target = null;
        for (int pos = 0; pos < numChildren() - 1; ++pos) {
            target = eval(environment, pos, target);
        }
        ASTree mostRightTree = child(numChildren() - 1);
        if (mostRightTree instanceof Dot) {
            if (target instanceof StoneObject) {
                return ((StoneObject) target).write(((Dot) mostRightTree).name(), rVal);
            }
            throw new ParseException("illegal assign with tar " + target);
        } else {
            //ArrayRef
            if (target instanceof Object[]) {
                Object index = mostRightTree.eval(environment);
                if (index instanceof Long) {
                    return ((Object[]) target)[((Long) index).intValue()] = rVal;
                } else {
                    throw new ParseException("require int but got " + index);
                }
            } else {
                throw new ParseException("require object[] but got " + target);
            }
        }

    }


    private Object eval(Environment env, int pos, Object target) {
        ASTree child = child(pos);
        if (pos == 0) {
            return child.eval(env);
        }
        if (child instanceof Dot) {
            Dot dot = (Dot) child;
            String method = dot.name();
            if ("new".equals(method)) {
                if (target instanceof ClassInfo) {
                    Environment nest = BasicEnvironment.newEnv(((ClassInfo) target).getEnvironment());
                    StoneObject so = new StoneObject(nest);
                    nest.putNew("this", so);
                    initObject((ClassInfo) target, nest);
                    return so;
                } else {
                    throw new ParseException("require classinfo object but got " + target);
                }
            } else if (target instanceof StoneObject) {
                return ((StoneObject) target).read(method);
            } else {
                throw new ParseException("require StoneObject but got " + target);
            }
        } else if (child instanceof ArrayRef) {
            //取数组索引
            if (target instanceof Object[]) {
                Object index = child.eval(env);
                if (index instanceof Long) {
                    return ((Object[]) target)[((Long) index).intValue()];
                } else {
                    throw new ParseException("require int but got " + index);
                }
            } else {
                throw new ParseException("require object[] but got " + target);
            }
        }
        return ((Args) child).eval(env, target);
    }

    protected void initObject(ClassInfo ci, Environment env) {
        if (ci.getSuperClass() != null)
            initObject(ci.getSuperClass(), env);
        ((ClassBody) ci.body()).eval(env);
    }
}
