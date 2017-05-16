package junit.suite;

import junit.categories.MyCategories;
import junit.tests.DataProviderRunnerTest;
import junit.tests.JUnitParamsTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({DataProviderRunnerTest.class, JUnitParamsTest.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(MyCategories.PositiveTests.class)
public class PositiveSuite{
}