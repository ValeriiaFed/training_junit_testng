package junit.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import junit.categories.MyCategories;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.createTempDirectory;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {

    private Path tempDirectoryPath;
    private Path filePath;

    @DataProvider
    public static Object[][] loadFileNamesFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                JUnitParamsTest.class.getResourceAsStream("/filename.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.toArray(new Object[][]{});
    }

    @Before
    public void setUp(){
        Path parentDirectoryPath = Paths.get("./temp");
        try {
            tempDirectoryPath = createTempDirectory(parentDirectoryPath, "prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Category(MyCategories.PositiveTests.class)
    @Parameters(method = "loadFileNamesFromFile")
    public void createTwoFilesWithDifferentNamesTest(String fileName1, String fileName2) throws IOException {
        System.out.println("createTwoFilesWithDifferentNamesTest positive");
        filePath = Paths.get(tempDirectoryPath + "/" + fileName1);
        Path filePath2 = Paths.get(tempDirectoryPath + "/" + fileName2);
        createFile(filePath);
        createFile(filePath2);
        File directory = new File(String.valueOf(tempDirectoryPath));
        Assert.assertTrue(directory.listFiles().length == 2);
    }

    @After
    public void tearDown(){
        File directory = new File(String.valueOf(tempDirectoryPath));
            for (File c : directory.listFiles()){
                c.delete();
        }
        directory.delete();
    }

    private static Object generateRandomFileName(){
        return "file" + new Random().nextInt() + ".txt";
    }
}


