package junit.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import junit.annotations.DataSource;
import junit.categories.MyCategories;
import junit.dataprovider.UniversalDataProvider;
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
import static junit.annotations.DataSource.Type.RESOURCE;

@RunWith(DataProviderRunner.class)
public class DataProviderRunnerTest {

    private Path tempDirectoryPath;
    private Path filePath;


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
    @UseDataProvider(value = "fileNamesLoader", location = UniversalDataProvider.class)
    @DataSource(value = "/filename.data", type = RESOURCE)
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


