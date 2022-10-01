package FrontEnd.FileFormaterPackage;

import org.junit.Before;
import org.junit.Test;

import java.io.File;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCompiler {
    private Compiler compiler;
    private String pattern;

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

        pattern = "\\d+\\. \\def -> \\ans";
    }

    @Test
    public void NoAlteration () {
        compiler = new Compiler(filePropre, pattern);
        assertTrue(compiler.compile());
    }

    @Test
    public void IgnoreFirstLineTrue() {
        compiler = new Compiler(fileIgnoreFirstLine, pattern);
        compiler.setIgnoreFirstLine(true);
        assertTrue(compiler.compile());
    }

    @Test
    public void IgnoreEmptyLinesTrue() {
        compiler = new Compiler(fileIgnoreEmptyLines, pattern);
        compiler.setIgnoreEmptyLines(true);
        assertTrue(compiler.compile());
    }

    @Test
    public void IgnoreNotFittingLinesTrue() {
        compiler = new Compiler(fileIgnoreNotFittingLine, pattern);
        compiler.setIgnoreNotFittingLines(true);
        assertTrue(compiler.compile());
    }

    @Test
    public void IgnoreFirstLineFalse() {
        compiler = new Compiler(fileIgnoreFirstLine, pattern);
        assertFalse(compiler.compile());
    }

    @Test
    public void IgnoreEmptyLinesFalse() {
        compiler = new Compiler(fileIgnoreEmptyLines, pattern);
        assertFalse(compiler.compile());
    }

    @Test
    public void IgnoreNotFittingLinesFalse(){
        compiler = new Compiler(fileIgnoreNotFittingLine, pattern);
        assertFalse(compiler.compile());
    }

    @Test
    public void getFaultyLine() {
        compiler = new Compiler(fileIgnoreNotFittingLine, pattern);
        compiler.compile();
        assertEquals("A. Andrzej -> Duda", compiler.getFaultyLine());
    }

    @Test
    public void getIndexOfFaultyLine() {
        compiler = new Compiler(fileIgnoreNotFittingLine, pattern);
        compiler.compile();
        assertEquals(2, compiler.getIndexOfFaultyLine());
    }
}