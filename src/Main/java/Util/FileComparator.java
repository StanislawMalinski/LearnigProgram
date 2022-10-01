package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class FileComparator {
    private final File expected;
    private final File actual;

    private BufferedReader BRExpected;
    private BufferedReader BRActual;

    private String expectedLine;
    private String actualLine;

    public FileComparator(File expected, File actual) {
        this.expected = expected;
        this.actual = actual;
        getBufferedReaderExpected();
        getBufferedReaderActual();
    }

    private void getBufferedReaderExpected(){
        try {
             BRExpected = new BufferedReader(new FileReader(expected));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void getBufferedReaderActual(){
        try {
            BRActual = new BufferedReader(new FileReader(actual));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean assertEqual(){
        String lineExpected;
        String lineActual;
        try {
            while (true) {
                lineExpected = BRExpected.readLine();
                lineActual = BRActual.readLine();
                if(lineActual == null && lineActual == null) {
                    closeBR();
                    return true;
                }
                if (!Objects.equals(lineExpected, lineActual)) {
                    closeBR();
                    expectedLine = lineExpected;
                    actualLine = lineActual;
                    printComparason();
                    return false;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            closeBR();
            return false;
        }
    }

    private void closeBR(){
        try {
            BRExpected.close();
            BRActual.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void printComparason(){
        System.out.println("Expected: " + expectedLine);
        System.out.println("Actual: " + actualLine);
    }
}
