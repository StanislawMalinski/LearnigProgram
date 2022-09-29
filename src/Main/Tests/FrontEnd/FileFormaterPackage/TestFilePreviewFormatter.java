package FrontEnd.FileFormaterPackage;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class TestFilePreviewFormatter {
    private FilePreviewFormatter formater;
    private Pattern pattern;

    private File filePropre;
    private File fileIgnoreFirstLine;
    private File fileIgnoreEmptyLines;
    private File fileIgnoreNotFittingLine;

    @Before
    public void setUp() {
        filePropre = new File("src/Main/TestResources/ProperFile.txt");
        fileIgnoreFirstLine = new File("src/Main/TestResources/FileWithFirstLines.txt");
        fileIgnoreEmptyLines = new File("src/Main/TestResources/FileWithEmptyLines.txt");
        fileIgnoreNotFittingLine = new File("src/Main/TestResources/FileWithNotFittingLine.txt");

        String sPattern = "\\d+\\. (?<def>.*) -> (?<ans>.*)";
        pattern = Pattern.compile(sPattern);
    }

    @Test
    public void NoAlteration () {
        formater = new FilePreviewFormatter(filePropre, pattern);
        assertTrue(formater.ValidateFile());
    }

    @Test
    public void IgnoreFirstLineTrue() {
        formater = new FilePreviewFormatter(fileIgnoreFirstLine, pattern);
        formater.setIgnoreFirstLine(true);
        assertTrue(formater.ValidateFile());
    }

    @Test
    public void IgnoreEmptyLinesTrue() {
        formater = new FilePreviewFormatter(fileIgnoreEmptyLines, pattern);
        formater.setIgnoreEmptyLines(true);
        assertTrue(formater.ValidateFile());
    }

    @Test
    public void IgnoreNotFittingLinesTrue() {
        formater = new FilePreviewFormatter(fileIgnoreNotFittingLine, pattern);
        formater.setIgnoreNotFittingLines(true);
        assertTrue(formater.ValidateFile());
    }

    @Test
    public void IgnoreFirstLineFalse() {
        formater = new FilePreviewFormatter(fileIgnoreFirstLine, pattern);
        assertFalse(formater.ValidateFile());
    }

    @Test
    public void IgnoreEmptyLinesFalse() {
        formater = new FilePreviewFormatter(fileIgnoreEmptyLines, pattern);
        assertFalse(formater.ValidateFile());
    }

    @Test
    public void IgnoreNotFittingLinesFalse(){
        formater = new FilePreviewFormatter(fileIgnoreNotFittingLine, pattern);
        assertFalse(formater.ValidateFile());
    }

    @Test
    public void getFaultyLine() {
        formater = new FilePreviewFormatter(fileIgnoreNotFittingLine, pattern);
        formater.ValidateFile();
        assertEquals("3. Maciek <- Szarecki",formater.getFaultyLine());
    }

    @Test
    public void getIndexOfFaultyLine() {
        formater = new FilePreviewFormatter(fileIgnoreNotFittingLine, pattern);
        formater.ValidateFile();
        assertEquals(2, formater.getIndexOfFaultyLine());
    }
}