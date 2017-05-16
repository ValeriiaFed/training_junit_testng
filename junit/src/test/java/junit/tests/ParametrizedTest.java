package junit.tests;

import junit.categories.MyCategories;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.createTempDirectory;

@RunWith(Parameterized.class)
public class ParametrizedTest {

    private Path tempDirectoryPath;
    private Path filePath;

    @Parameter(0)
    public String fileName;

    @Parameters(name="fileName:{0}")
    public static Collection<Object[]> fileNames(){
        return Arrays.asList(new Object[][]{
                {generateRandomFileName()},
                {generateRandomFileName()}
        });
    }

    @Test
    @Category(MyCategories.NegativeTests.class)
    public void createFileWithExistingNameTest() throws IOException {
        System.out.println("createFileWithExistingNameTest negative");
        filePath = Paths.get(tempDirectoryPath + "/" + fileName);
        createFile(filePath);
        try{
            createFile(filePath);
            Assert.fail("File with the same name should not be created");
        } catch (FileAlreadyExistsException e){
            File directory = new File(String.valueOf(tempDirectoryPath));
            Assert.assertTrue(directory.listFiles().length == 1);
        }
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
    public void createFileTest() throws IOException {
        System.out.println("createFileTest positive");
        filePath = Paths.get(tempDirectoryPath + "/" + fileName);
        Path createdFile = createFile(filePath);
        Assert.assertEquals(createdFile, filePath);
        File directory = new File(String.valueOf(tempDirectoryPath));
        Assert.assertTrue(directory.listFiles().length == 1);
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


