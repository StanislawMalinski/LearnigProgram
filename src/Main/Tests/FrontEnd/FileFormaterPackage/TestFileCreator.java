package FrontEnd.FileFormaterPackage;

import Util.Information;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileCreator {
    private static FileCreator creator1;
    private static FileCreator creator2;
    private static FileCreator creator3;
    private static FileCreator creator4;

    @BeforeAll
    public static void setUp(){
        creator1 = new FileCreator("Stanisław1");
        creator2 = new FileCreator("Stanisław1.txt");
        creator3 = new FileCreator("Stanisław1.txtd");
        creator4 = new FileCreator("Stanisław1.tx");
    }

    @BeforeEach
    public void resetVersioning(){
        FileCreator.setVersioning(false);
    }

    @AfterEach
    public void deleteAllFileIfSomeTestsFail(){
        creator1.deleteFile();
        creator2.deleteFile();
        creator3.deleteFile();
        creator4.deleteFile();
    }

    @Test
    public void validateFilename() {
        assertTrue(creator1.ValidateFilename());
    }

    @Test
    public void validateFilenameThatIsTaken() {
        creator1.CreateFile();
        //FileCreator.setVersioning(false); //TODO why doesn't work in @BeforEach
        assertFalse(creator2.ValidateFilename());
        creator1.deleteFile();
    }

    @Test
    public void validateFilenameThatIsTakenButVersioningIsPossible() {
        creator1.CreateFile();
        FileCreator.setVersioning(true);
        assertTrue(creator2.ValidateFilename());
        creator1.deleteFile();
    }

    @Test
    public void validateFilnameInvalidFilname1() {
        assertFalse(creator3.ValidateFilename());
    }

    @Test
    public void validateFilnameInvalidFilnam2() {
        assertFalse(creator4.ValidateFilename());
    }

    @Test
    public void createFile() {
        File file = new File(Information.locationOfTheSubjects + "Stanisław1.txt");
        assertFalse(file.exists());
        creator1.CreateFile();
        assertTrue(file.exists());
        creator1.deleteFile();
    }

    @Test
    public void createFileThatExistsWithoutVersioning() {
        File file = new File(Information.locationOfTheSubjects + "Stanisław1(1).txt");
        creator1.CreateFile();
        assertFalse(file.exists());
        creator2.CreateFile();
        assertFalse(file.exists());
        creator1.deleteFile();
        creator2.deleteFile();
    }

    @Test
    public void createFileThatExistsWithVersioning() {
        File file = new File(Information.locationOfTheSubjects + "Stanisław1(1).txt");
        creator1.CreateFile();
        FileCreator.setVersioning(true);
        assertFalse(file.exists());
        creator2.CreateFile();
        assertTrue(file.exists());
        creator1.deleteFile();
        creator2.deleteFile();
    }

    @Test
    public void createFileInvalidWithInvalidFileName1() {
        try {
            creator3.CreateFile();
            fail();
        } catch (InvalidParameterException e){
            ;
        }
    }

    @Test
    public void createFileInvalidWithInvalidFileName2() {
        try {
            creator4.CreateFile();
            fail();
        } catch (InvalidParameterException e){
            ;
        }
    }
}