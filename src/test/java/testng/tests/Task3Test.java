package testng.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task3Test {
    private Path tempDirectoryPath;
    private Path filePath;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        Path parentDirectoryPath = Paths.get("./temp");
        try {
            tempDirectoryPath = Files.createTempDirectory(parentDirectoryPath, "prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "positive")
    public void createFileTest() throws IOException {
        System.out.println("createFileTest positive");
        filePath = Paths.get(tempDirectoryPath + "/file1.txt");
        Path createdFile = Files.createFile(filePath);
        Assert.assertEquals(createdFile, filePath);
        File directory = new File(String.valueOf(tempDirectoryPath));
        Assert.assertTrue(directory.listFiles().length == 1);
    }

    @Test(groups = "negative")
    public void createFileWithExistingNameTest() throws IOException {
        System.out.println("createFileWithExistingNameTest negative");
        filePath = Paths.get(tempDirectoryPath + "/file1.txt");
        Files.createFile(filePath);
        try{
            Files.createFile(filePath);
            Assert.fail("File with the same name should not be created");
        } catch (FileAlreadyExistsException e){
            File directory = new File(String.valueOf(tempDirectoryPath));
            Assert.assertTrue(directory.listFiles().length == 1);
        }
    }

    @Test(groups = "positive")
    public void createTwoFilesWithDifferentNamesTest() throws IOException {
        System.out.println("createTwoFilesWithDifferentNamesTest positive");
        filePath = Paths.get(tempDirectoryPath + "/file1.txt");
        Path filePath2 = Paths.get(tempDirectoryPath + "/file2.txt");
        Files.createFile(filePath);
        Files.createFile(filePath2);
        File directory = new File(String.valueOf(tempDirectoryPath));
        Assert.assertTrue(directory.listFiles().length == 2);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        File directory = new File(String.valueOf(tempDirectoryPath));
            for (File c : directory.listFiles()){
                c.delete();
        }
    }

}
