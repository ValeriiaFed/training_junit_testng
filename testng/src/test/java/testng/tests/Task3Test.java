package testng.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    @Test(groups = {"positive"}, dataProvider = "fileNameGenerator")
    public void createFileTest(String fileName) throws IOException {
        System.out.println("createFileTest positive");
        filePath = Paths.get(tempDirectoryPath + "/" + fileName);
        Path createdFile = Files.createFile(filePath);
        Assert.assertEquals(createdFile, filePath);
        File directory = new File(String.valueOf(tempDirectoryPath));
        Assert.assertTrue(directory.listFiles().length == 1);
    }

    @Test(groups = {"negative"}, dataProvider = "fileNameGenerator")
    public void createFileWithExistingNameTest(String fileName) throws IOException {
        System.out.println("createFileWithExistingNameTest negative");
        filePath = Paths.get(tempDirectoryPath + "/" + fileName);
        Files.createFile(filePath);
        try{
            Files.createFile(filePath);
            Assert.fail("File with the same name should not be created");
        } catch (FileAlreadyExistsException e){
            File directory = new File(String.valueOf(tempDirectoryPath));
            Assert.assertTrue(directory.listFiles().length == 1);
        }
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadFileNamesFromFile")
    public void createTwoFilesWithDifferentNamesTest(String fileName1, String fileName2) throws IOException {
        System.out.println("createTwoFilesWithDifferentNamesTest positive");
        filePath = Paths.get(tempDirectoryPath + "/" + fileName1);
        Path filePath2 = Paths.get(tempDirectoryPath + "/" + fileName2);
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
        directory.delete();
    }

    @DataProvider
    public Iterator<Object[]> fileNameGenerator() {
        List<Object[]> data = new ArrayList<Object[]>();
        for (int i = 0; i < 5; i++) {
            data.add(new Object[]{
                    generateRandomFileName()
            });
        }
        return data.iterator();
    }

    private Object generateRandomFileName(){
        return "file" + new Random().nextInt() + ".txt";
    }
}


