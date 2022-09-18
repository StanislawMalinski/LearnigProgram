package Util.FileFormaterPackage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileFormater {
    private File file;
    private TextField TF;
    private Label Comunicator;
    private boolean ignoreFirstLine;
    private String FaultyLine;
    private int FaultyLineIndex;

    protected FileFormater(File file){
        this.file = file;
    }

    protected int CompilePattern(String Spattern) {
        try {
            if( !Spattern.contains("def") || !Spattern.contains("ans")) return 4;
            Spattern = Spattern.replace("def", "(.*)");
            Spattern = Spattern.replace("ans", "(.*)");
            Pattern pattern = Pattern.compile(Spattern);
            if(!isAplicapble(pattern)) return 2;
            return 3;
        } catch (PatternSyntaxException e){
            return 1;
        }
    }

    private boolean isAplicapble(Pattern pattern){
        BufferedReader BR = getBufferdReaderForFile();
        String line = "";
        Matcher m;
        int lineNumber = 0;
        //\[.*\] def -{1} ans
        try {
            while ((line = BR.readLine()) != null){
                if(lineNumber == 0 && ignoreFirstLine) {
                    lineNumber++;
                    continue;
                }
                m = pattern.matcher(line);
                if(!m.matches()) {
                    FaultyLine = line;
                    FaultyLineIndex = lineNumber;
                    return false;
                }
                lineNumber++;
            }
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    protected String [] getHeadFromFile(){
        BufferedReader BR = getBufferdReaderForFile();
        String line;
        String firstLine = "";
        String middle = "";
        String endLine = "";
        try {
            for (int i = 0; i < 11; i++) {
                line = BR.readLine();
                if (line == null) break;
                if( i == 0) {
                    firstLine = line;
                } else if( i < 10) {
                    middle += line + "\n";
                } else {
                    endLine = line;
                }
            }
            String [] out = new String [3];
            out[0] = firstLine;
            out[1] = middle;
            out[2] = endLine;
            return out;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedReader getBufferdReaderForFile(){
        try {
            return new BufferedReader(new FileReader(file));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    protected File getReformatedFile(){
        return new File("");
    }

    protected String getFaultyLine(){
        return FaultyLine;
    }

    protected int getFaultyLineIndex() {
        return FaultyLineIndex;
    }

    protected void setIgnoreFirstLine(boolean b){
        ignoreFirstLine = b;
    }
}
