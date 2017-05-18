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
                int amountOfTry = description.getAnnotation(Unstable.class).amountOfTry();
                for (int i = 1; i <=amountOfTry; i++){
                    if (i == amountOfTry){
                        System.out.println("Attempt " + amountOfTry);
                        statement.evaluate();
                    }
                    try {
                        statement.evaluate();
                        System.out.println("Test passed");
                        break;
                    } catch (Throwable t) {
                        System.out.println(String.format("Attempt %s is failed", i));
                    }
                }
            } else {
                statement.evaluate();
            }
        }
    }
}
