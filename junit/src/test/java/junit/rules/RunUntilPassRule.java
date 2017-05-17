package junit.rules;

import junit.annotations.Unstable;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RunUntilPassRule implements TestRule {

    public Statement apply(Statement statement, Description description) {
        return new RunUntilPassStatement(statement, description);
    }

    public class RunUntilPassStatement extends Statement{

        private final Statement statement;
        private Description description;

        public RunUntilPassStatement(Statement statement, Description description) {
            this.statement = statement;
            this.description = description;
        }

        public void evaluate() throws Throwable {
            if (description.getAnnotation(Unstable.class) != null) {
                for (int i = 0; i < description.getAnnotation(Unstable.class).amountOfTry(); i++){
                    try {
                        statement.evaluate();
                        System.out.println("Test passed");
                        break;
                    } catch (Throwable t) {
                        System.out.println("Attempt is failed");
                    }
                }
            }
        }
    }
}
