package junit.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import junit.categories.MyCategories;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.createTempDirectory;

@RunWith(DataProviderRunner.class)
public class DataProviderRunnerTest {

    private Path tempDirectoryPath;
    private Path filePath;

    @DataProvider
    public static Object[][] loadFileNamesFromFile(){
        return new Object[][]{
                {generateRandomFileName(), generateRandomFileName()},
                {generateRandomFileName(), generateRandomFileName()}
        };
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
    @UseDataProvider("loadFileNamesFromFile")
    @Category(MyCategories.PositiveTests.class)
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


