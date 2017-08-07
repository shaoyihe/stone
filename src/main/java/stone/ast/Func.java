package stone.ast;

import stone.BasicEnvironment;
import stone.Environment;

/**
 * Created by heshaoyi on 8/6/17.
 */
public class Func {
    private Parameters parameters;
    private BlockStmnt body;
    private Environment environment;

    public Func(Parameters parameters, BlockStmnt body, Environment environment) {
        this.body = body;
        this.environment = environment;
        this.parameters = parameters;
    }

    public BlockStmnt getBody() {
        return body;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public Environment makeEnv() {
        return BasicEnvironment.newEnv(environment);
    }


    @Override
    public String toString() {
        return "Func{" +
                "body=" + body +
                ", parameters=" + parameters +
                ", environment=" + environment +
                '}';
    }
}
