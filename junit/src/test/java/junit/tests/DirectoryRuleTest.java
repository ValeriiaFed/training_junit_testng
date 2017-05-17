package junit.tests;

import junit.annotations.Unstable;
import junit.rules.Fixture;
import junit.rules.RunUntilPassRule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.Files.createFile;

public class DirectoryRuleTest extends Fixture {

    private Path filePath;
    
    @Test
    @Unstable(amountOfTry = 10)
    public void createFileWithExistingNameTest() throws IOException {
        System.out.println("createFileWithExistingNameTest negative");
        filePath = Paths.get(tempDirectoryPath.getTempDirectoryPath() + "/" + generateRandomFileName());
        createFile(filePath);
        try{
            createFile(filePath);
            Assert.fail("File with the same name should not be created");
        } catch (FileAlreadyExistsException e){
            File directory = new File(String.valueOf(tempDirectoryPath.getTempDirectoryPath()));
            Assert.assertTrue(directory.listFiles().length == 1);
        }
    }

    private static Object generateRandomFileName(){
        return "file" + new Random().nextInt() + ".txt";
    }
}


