package junit.rules;

import org.junit.ClassRule;
import org.junit.Rule;

public class Fixture {

    @ClassRule
    public static DirectoryRule tempDirectoryPath = new DirectoryRule();

    @Rule
    public RunUntilPassRule runUntilPassRule = new RunUntilPassRule();

}


