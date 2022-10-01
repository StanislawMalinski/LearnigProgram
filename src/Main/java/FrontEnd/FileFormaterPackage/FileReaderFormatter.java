package FrontEnd.FileFormaterPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FileReaderFormatter {
    private File file;
    private Pattern pattern;
    private Matcher matcher;

    private String faultyLine;
    private int indexOfFaultyLine;

    private boolean IgnoreFirstLine;
    private boolean IgnoreEmptyLines;
    private boolean IgnoreNotFittingLines;

    public FileReaderFormatter(File file) {
        this.file = file;
    }

    public FileReaderFormatter(File file, Pattern pattern){
        this.file = file;
        setPattern(pattern);
    }

    public FileReaderFormatter(File file, String SPattern){
        this.file = file;
        if( !SPattern.contains("\\def") || !SPattern.contains("\\ans"))
            throw new InvalidParameterException("Pattern doesn't contains \"\\def\" or \"\\ans\"." );
        Pattern pattern = convertPattern(SPattern);
        setPattern(pattern);
    }

    private Pattern convertPattern(String pattern){
        pattern = pattern.replace("\\def", "(?<def>.*)");
        pattern = pattern.replace("\\ans", "(?<ans>.*)");
        pattern = pattern.replace("\\hint", "(?<hint>.*)");
        return Pattern.compile(pattern);
        //TODO add format and aspects
    }

    protected void performAction(){
        try{
            BufferedReader BR = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;
            while((line = BR.readLine()) != null){
                chooseAction(line,lineNumber++);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void chooseAction(String line, int lineNumber){
        boolean isMatching = true;
        if(pattern != null) {
            matcher = pattern.matcher(line);
            isMatching = matcher.matches();
        }
        if(IgnoreFirstLine && lineNumber == 0){
            ActionIgnore(line,lineNumber);
        } else if (IgnoreEmptyLines && isLineEmpty(line)) {
            ActionIgnore(line,lineNumber);
        } else if (IgnoreNotFittingLines && !isMatching) {
            ActionIgnore(line,lineNumber);
        } else if (!isMatching){
            ActionNotWritable(line,lineNumber);
        } else {
            ActionWritable(line,lineNumber);
        }
    }

    private boolean isLineEmpty(String line) {
        char [] table = line.toCharArray();
        for(char c : table){
            if(!Character.isWhitespace(c)) return false;
        }
        return true;
    }

    protected abstract void ActionIgnore(String line, int numberLine);

    protected abstract void ActionNotWritable(String line, int numberLine);

    protected abstract void ActionWritable(String line, int numberLine);


    public void setIgnoreFirstLine(boolean ignoreFirstLine) {
        IgnoreFirstLine = ignoreFirstLine;
    }

    public void setIgnoreEmptyLines(boolean ignoreEmptyLines) {
        IgnoreEmptyLines = ignoreEmptyLines;
    }

    public void setIgnoreNotFittingLines(boolean ignoreNotFittingLines) {
        IgnoreNotFittingLines = ignoreNotFittingLines;
    }

    public boolean isIgnoreFirstLine() {
        return IgnoreFirstLine;
    }

    public boolean isIgnoreEmptyLines() {
        return IgnoreEmptyLines;
    }

    public boolean isIgnoreNotFittingLines() {
        return IgnoreNotFittingLines;
    }

    public void setPattern(Pattern pattern){
        this.pattern = pattern;
    }

    public void setPattern(String pattern){
        this.pattern = convertPattern(pattern);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getFaultyLine() {
        return faultyLine;
    }

    public void setFaultyLine(String faultyLine) {
        this.faultyLine = faultyLine;
    }

    public int getIndexOfFaultyLine() {
        return indexOfFaultyLine;
    }

    public void setIndexOfFaultyLine(int indexOfFaultyLine) {
        this.indexOfFaultyLine = indexOfFaultyLine;
    }
}


/*
    kompiluje
    formatuje
    pisze dokument tekstowy
 */