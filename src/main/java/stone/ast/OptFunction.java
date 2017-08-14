package stone.ast;

import stone.Environment;

/**
 * Created by heshaoyi on 8/14/17.
 */
public class OptFunction extends Func {
    protected int size;

    public OptFunction(Parameters parameters, BlockStmnt body, Environment env, int memorySize) {
        super(parameters, body, env);
        size = memorySize;
    }

    @Override
    public Environment makeEnv() {
        return new ArrayEnv(size, environment);
    }
}