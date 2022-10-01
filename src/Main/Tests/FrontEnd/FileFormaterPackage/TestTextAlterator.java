package FrontEnd.FileFormaterPackage;

import Util.InvalidFileStructureException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestTextAlterator {

    private TextAlterator alterator;
    private String pattern;

    private File filePropre;
    private File fileIgnoreFirstLine;
    private File fileIgnoreEmptyLines;
    private File fileIgnoreNotFittingLine;

    private String desirableText =
            """
            1. Stanisław -> Maliński
            2. Hania -> Czaban
            3. Maciek -> Szarecki
            4. Mikołaj -> Kowalczyk
            5. Adam -> Kubica""";

    @Before
    public void setUp() {
        filePropre = new File("src/Main/TestResources/ProperFile.txt");
        fileIgnoreFirstLine = new File("src/Main/TestResources/FileWithFirstLines.txt");
        fileIgnoreEmptyLines = new File("src/Main/TestResources/FileWithEmptyLines.txt");
        fileIgnoreNotFittingLine = new File("src/Main/TestResources/FileWithNotFittingLine.txt");

        pattern = "\\d+\\. \\def -> \\ans";
    }

    @Test
    public void NoAlteration () {
        alterator = new TextAlterator(filePropre, pattern);
        assertEquals(desirableText, alterator.getAlteredText());
    }

    @Test
    public void IgnoreFirstLineTrue() {
        alterator = new TextAlterator(fileIgnoreFirstLine, pattern);
        alterator.setIgnoreFirstLine(true);
        assertEquals(desirableText, alterator.getAlteredText());
    }

    @Test
    public void IgnoreEmptyLinesTrue() {
        alterator = new TextAlterator(fileIgnoreEmptyLines, pattern);
        alterator.setIgnoreEmptyLines(true);
        assertEquals(desirableText, alterator.getAlteredText());
    }

    @Test
    public void IgnoreNotFittingLinesTrue() {
        alterator = new TextAlterator(fileIgnoreNotFittingLine, pattern);
        alterator.setIgnoreNotFittingLines(true);
        assertEquals(desirableText, alterator.getAlteredText());
    }
}