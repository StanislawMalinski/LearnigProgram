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

    private String FaultyLine;
    private int FaultyLineIndex;
    private Pattern pattern;
    private Matcher m;
    private FilePreviewFormatter formater;

    private boolean patContainHint;
    private boolean patContainFormat;
    private boolean patContainAspects;

    private boolean IgnoreFirstLine;
    private boolean IgnoreEmptyLines;
    private boolean IgnoreNotFittingLines;

    protected FileFormater(File file){
        this.file = file;
    }

    protected int CompilePattern(String Spattern) {
        try {
            if( !Spattern.contains("\\def") || !Spattern.contains("\\ans"))
                return 4;
            Pattern pattern = convertPattern(Spattern);
            initilizeFormater(pattern);
            if(formater.ValidateFile()) {
                FaultyLine = formater.getFaultyLine();
                FaultyLineIndex = formater.getIndexOfFaultyLine();
                return 2;
            }
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

    private void initilizeFormater(Pattern pattern){
        formater = new FilePreviewFormatter(file,pattern);
        formater.setIgnoreFirstLine(IgnoreFirstLine);
        formater.setIgnoreEmptyLines(IgnoreEmptyLines);
        formater.setIgnoreNotFittingLines(IgnoreNotFittingLines);
    }

    protected String getAlteredText(){
        return formater.getAlteredText();
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
        File Alterd = new File("tmp.txt");
        formater.getAlteredTextInFile(Alterd);
        BufferedReader BR = getBufferdReaderForFile(Alterd);
        BufferedWriter BW = getBufferdWriterForFile(Reformated);
        String line;
        String newLine;
        int numberLine = 0;
        try {
            while ((line = BR.readLine()) != null){
                newLine = getFormatedLine(line, numberLine);
                BW.write(newLine);
                numberLine++;
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
        if(IgnoreNotFittingLines && !m.matches()) return "";
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

    protected void setPattern(String pattern){
        this.pattern = convertPattern(pattern);
    }

    public void setIgnoreFirstLine(boolean selected) {
        IgnoreFirstLine = selected;
    }

    public void setIgnoreEmptyLines(boolean selected) {
        IgnoreEmptyLines = selected;
    }

    public void setIgnoreNptFittingLines(boolean selected) {
        IgnoreNotFittingLines = selected;
    }
}
