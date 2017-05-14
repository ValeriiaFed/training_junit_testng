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
    private Path tempDirectory;
    private Path filePath;

    @BeforeMethod
    public void setUp(){
        Path parentDirectoryPath = Paths.get("./temp");
        try {
            tempDirectory = Files.createTempDirectory(parentDirectoryPath, "prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFileTest() throws IOException {
        filePath = Paths.get(tempDirectory + "/file1.txt");
        Files.createFile(filePath);
    }

    @Test
    public void createFileWithExistingNameTest() throws IOException {
        filePath = Paths.get(tempDirectory + "/file1.txt");
        Files.createFile(filePath);
        try{
            Files.createFile(filePath);
            Assert.fail("File with the same name should not be created");
        } catch (FileAlreadyExistsException e){

        }
    }

    @Test
    public void createTwoFilesWithDifferentNamesTest() throws IOException {
        filePath = Paths.get(tempDirectory + "/file1.txt");
        Path filePath2 = Paths.get(tempDirectory + "/file2.txt");
        Files.createFile(filePath);
        Files.createFile(filePath2);
    }

    @AfterMethod
    public void tearDown(){
        File directory = new File(String.valueOf(tempDirectory));
            for (File c : directory.listFiles()){
                c.delete();
        }
    }

}
