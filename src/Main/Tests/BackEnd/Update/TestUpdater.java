package BackEnd.Update;

import BackEnd.Material;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TestUpdater {
    private static Material material;
    private static Updater updater;
    private static File file;
    private BufferedReader bufferedReader;

    @BeforeAll
    public static void Initialize(){
        material = new Material(new File("src/Main/TestResources/WłoskiCoded.txt"));
        updater = new Updater(material);

        material.getQuestion(1).addAttempt();
        material.getQuestion(2).addAttempt();
        material.getQuestion(2).addSuccess();
        material.getQuestion(3).addSuccess();
        material.getQuestion(4).addAttempt();
        material.getQuestion(4).addSuccess();
        material.getQuestion(4).addAttempt();
        material.getQuestion(4).addSuccess();

        try {
            file = updater.generateNewFile();
        } catch (IOException e){
            fail();
        }
    }

    @BeforeEach
    public void InitializeBufferedReader(){
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException e){
            fail();
        }
    }

    @Test
    public void TestSetInfo(){
        InitializeBufferedReader();
        String line = "";
        int NumberLine = 0;
        try {
            for(int i = 0; i <= NumberLine; i++)
                line = bufferedReader.readLine();
        } catch (IOException e){
            fail();
        }
        LocalDate myObj = LocalDate.now();
        Pattern pattern = Pattern.compile("\\$\\(<lastLearned>(?<lastLearned>.*)\\)\\$\\(<time>(?<time>.*)\\)\\$");
        Matcher m = pattern.matcher(line);

        assertTrue(m.matches());
        assertEquals(m.group("lastLearned"), myObj.toString());
        assertTrue(m.group("time").equals("0:0"));
    }

    @Test
    public void Test1(){
        assertTrue(Test(2, "a"));
    }

    @Test
    public void Test2(){
        assertTrue(Test(3, "as"));
    }

    @Test
    public void Test3(){
        assertTrue(Test(4, "s"));
    }

    @Test
    public void Test4() {
        assertTrue(Test(5, "asas"));
    }

    private boolean Test(int lineNumber, String expect){
        InitializeBufferedReader();
        String line = "";
        int NumberLine = lineNumber;
        try {
            for(int i = 0; i <= NumberLine; i++)
                line = bufferedReader.readLine();
        } catch (IOException e){
            fail();
        }
        Pattern pattern = Pattern.compile(".*\\$\\(<sequence>(?<sequence>\\w*)\\)\\$.*");
        Matcher m = pattern.matcher(line);

        assertTrue(m.matches());
        assertEquals(m.group("sequence"), expect);
        return true;
    }

    @AfterAll
    public static void cleanFile(){
        updater.removeTmpFile();
    }
}