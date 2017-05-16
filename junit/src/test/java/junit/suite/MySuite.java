package junit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.tests.ParametrizedTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(ParametrizedTest.class)
public class MySuite {
}
