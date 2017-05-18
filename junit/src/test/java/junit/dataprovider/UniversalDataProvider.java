package junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import junit.annotations.DataSource;
import junit.tests.DataProviderRunnerTest;
import org.junit.runners.model.FrameworkMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UniversalDataProvider {

    @DataProvider
    public static Object[][] fileNamesLoader(FrameworkMethod testMethod) throws IOException {
        DataSource ds = testMethod.getAnnotation(DataSource.class);
        if (ds == null){
            throw new Error("There is no DataSource annotation");
        }
        switch (ds.type()){
            case RESOURCE:
                return loadDataFromResource(ds.value());
            case FILE:
                return loadDataFromFile(ds.value());
            default:
                throw new Error("DataSource type is not supported");
        }
    }

    private static Object[][] loadDataFromFile(String value) throws IOException {
        return loadDataFromInputStream(new FileInputStream(new File(value)));
    }

    private static Object[][] loadDataFromResource(String value) throws IOException {
        return loadDataFromInputStream(UniversalDataProvider.class.getResourceAsStream(value));
    }

    private static Object[][] loadDataFromInputStream(InputStream input) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviderRunnerTest.class.getResourceAsStream("/filename.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.toArray(new Object[][]{});
    }
}
