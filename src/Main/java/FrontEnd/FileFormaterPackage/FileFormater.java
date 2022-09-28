package FrontEnd.FileFormaterPackage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class FileFormater {
    private File file;
    private TextField TF;
    private Label Comunicator;
    private boolean FirstLineAsATittle;
    private String FaultyLine;
    private int FaultyLineIndex;
    private Pattern pattern;
    private Matcher m;

    private boolean patContainHint;
    private boolean patContainFormat;
    private boolean patContainAspects;

    protected FileFormater(File file){
        this.file = file;
    }

    protected int CompilePattern(String Spattern) {
        try {
            if( !Spattern.contains("\\def") || !Spattern.contains("\\ans")) return 4;
            Pattern pattern = convertPattern(Spattern);
            if(!isAplicapble(pattern)) return 2;
            return 3;
        } catch (PatternSyntaxException e){
            return 1;
        }
    }

    private Pattern convertPattern(String pattern){
        pattern = pattern.replace("\\def", "(?<def>.*)");
        pattern = pattern.replace("\\ans", "(?<ans>.*)");
        pattern = pattern.replace("\\hint", "(?<hint>.*)");
        return Pattern.compile(pattern);
        //TODO add format and aspects
    }

    private boolean isAplicapble(Pattern pattern){
        BufferedReader BR = getBufferdReaderForFile(file);
        String line = "";
        int lineNumber = 0;
        try {
            while ((line = BR.readLine()) != null){
                if(lineNumber == 0 && FirstLineAsATittle) {
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

    protected String getFirstLine() {
        BufferedReader BR = getBufferdReaderForFile(file);
        try {
            return BR.readLine();
        } catch (IOException e){
            return null;
        }
    }

    protected String [] getHeadFromFile(){
        BufferedReader BR = getBufferdReaderForFile(file);
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
            BR.close();
            return out;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedReader getBufferdReaderForFile(File file){
        try {
            return new BufferedReader(new FileReader(file));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    protected File getStandardReformattedFile(){
        decodeStructureOfPattern();
        File Reformated = getNewFile();
        BufferedReader BR = getBufferdReaderForFile(file);
        BufferedWriter BW = getBufferdWriterForFile(Reformated);
        String oldLine;
        String newLine;
        int line = 0;
        try {
            while ((oldLine = BR.readLine()) != null){
                if(FirstLineAsATittle && line == 0) {
                    BW.write(oldLine + "\n");
                } else {
                    newLine = getFormatedLine(oldLine, line);
                    BW.write(newLine);
                }
                line++;
            }
            BR.close();
            BW.close();
            return Reformated;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void decodeStructureOfPattern() {
        patContainHint = pattern.pattern().contains("(?<hint>");
        patContainFormat = pattern.pattern().contains("(?<format>");
        patContainAspects = pattern.pattern().contains("(?<aspect1>");
    }

    private File getNewFile(){
        File newFile;
        String fileName = file.getAbsolutePath();
        String newFileName;
        int version = 1;
        while(true) {
            newFileName = fileName.replace(".txt", "(" + version + ").txt");
            newFile = new File(newFileName);
            try {
                if (newFile.createNewFile()) {
                    return newFile;
                } else
                    version++;
            } catch (IOException e) {
                return null;
            }
        }
    }

    private BufferedWriter getBufferdWriterForFile(File file) {
        try {
            return new BufferedWriter(new FileWriter(file));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String getFormatedLine(String oldLine, int line){
        StringBuilder newLine = new StringBuilder();
        m = pattern.matcher(oldLine);
        m.matches();
        newLine.append("$(<id>").append(line).append(")$");
        newLine.append("(<def>").append(m.group("def")).append(")$");
        newLine.append("(<ans>").append(m.group("ans")).append(")$");
        newLine.append("(<sequence>)$");
        newLine.append("(<att>0)$");
        newLine.append("(<suc>0)$");
        if(patContainHint) newLine.append("(<hint>").append(m.group("hint")).append(")$");
        if(patContainFormat) newLine.append("(<format>").append(m.group("format")).append(")$");
        for(int i = 0; patContainAspects; i++){
            if(oldLine.contains("<aspect" + i + ">"))
                newLine.append("(<aspect" + i + ">").append(m.group("aspect" + i)).append(")$");
            else
                break;
        }
        newLine.append("\n");
        return newLine.toString();
    }

    protected String getFaultyLine(){
        return FaultyLine;
    }

    protected int getFaultyLineIndex() {
        return FaultyLineIndex;
    }

    protected void setFirstLineAsATittle(boolean b){
        FirstLineAsATittle = b;
    }

    protected void setPattern(String pattern){
        this.pattern = convertPattern(pattern);
    }
}
