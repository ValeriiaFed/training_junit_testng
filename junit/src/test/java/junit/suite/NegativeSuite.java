package junit.suite;

import junit.categories.MyCategories;
import junit.tests.ParametrizedTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({ParametrizedTest.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(MyCategories.NegativeTests.class)
public class NegativeSuite {
}