package FrontEnd.FileFormaterPackage;

import Util.FileComparator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.naming.InsufficientResourcesException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestFileFormatingWriter {
    private FileFormatingWriter writer;
    private static String pattern;

    private static File Output;

    private static File fileExpected;

    private static File filePropre;
    private static File fileIgnoreFirstLine;
    private static File fileIgnoreEmptyLines;
    private static File fileIgnoreNotFittingLine;

    @BeforeAll
    public static void setUp() {
        Output = new File("src/Main/TestResources/tmp.txt");
        fileExpected = new File("src/Main/TestResources/CodedExpected.txt");
        filePropre = new File("src/Main/TestResources/ProperFile.txt");
        fileIgnoreFirstLine = new File("src/Main/TestResources/FileWithFirstLines.txt");
        fileIgnoreEmptyLines = new File("src/Main/TestResources/FileWithEmptyLines.txt");
        fileIgnoreNotFittingLine = new File("src/Main/TestResources/FileWithNotFittingLine.txt");

        pattern = "\\d+\\. \\def -> \\ans";
    }

    @BeforeEach
    public void InitilizeOutputFile(){
        try {
            Output.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void deleteOutputFile(){
        Output.delete();
    }

    @Test
    public void NoAlteration () {
        writer = new FileFormatingWriter(filePropre, pattern);
        writer.setOutputStream(Output);
        try {
            writer.write();
        } catch (InsufficientResourcesException e){
            fail();
        }
        FileComparator comparator = new FileComparator(fileExpected,Output);
        assertTrue(comparator.assertEqual());
    }

    @Test
    public void IgnoreFirstLineTrue() {
        writer = new FileFormatingWriter(fileIgnoreFirstLine, pattern);
        writer.setIgnoreFirstLine(true);
        writer.setOutputStream(Output);
        try {
            writer.write();
        } catch (InsufficientResourcesException e){
            fail();
        }
        FileComparator comparator = new FileComparator(fileExpected,Output);
        assertTrue(comparator.assertEqual());
    }

    @Test
    public void IgnoreEmptyLinesTrue() {
        writer = new FileFormatingWriter(fileIgnoreEmptyLines, pattern);
        writer.setIgnoreEmptyLines(true);
        writer.setOutputStream(Output);
        try {
            writer.write();
        } catch (InsufficientResourcesException e){
            fail();
        }
        FileComparator comparator = new FileComparator(fileExpected,Output);
        assertTrue(comparator.assertEqual());
    }

    @Test
    public void IgnoreNotFittingLinesTrue() {
        writer = new FileFormatingWriter(fileIgnoreNotFittingLine, pattern);
        writer.setIgnoreNotFittingLines(true);
        writer.setOutputStream(Output);
        try {
            writer.write();
        } catch (InsufficientResourcesException e){
            fail();
        }
        FileComparator comparator = new FileComparator(fileExpected,Output);
        assertTrue(comparator.assertEqual());
    }
}