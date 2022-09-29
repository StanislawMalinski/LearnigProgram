package FrontEnd.FileFormaterPackage;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePreviewFormatter {
    private final File priviewdFile;
    private final Pattern pattern;
    private Matcher matcher;

    private boolean IgnoreFirstLine = false;
    private boolean IgnoreEmptyLines = false;
    private boolean IgnoreNotFittingLines = false;

    private String FaultyLine;
    private int indexOfFaultyLine;

    protected FilePreviewFormatter(File file, Pattern pattern){
        priviewdFile = file;
        this.pattern = pattern;
    }

    protected void getAlteredTextInFile(File output) {
        try{
            BufferedWriter BR = new BufferedWriter(new FileWriter(output));
            BR.write(getAlteredText());
            BR.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected String getAlteredText(){
        try{
            String line;
            int lineNumber = 0;
            BufferedReader BR = new BufferedReader(new FileReader(priviewdFile));
            StringBuilder builder = new StringBuilder();
            while((line = BR.readLine()) != null){
               switch (isLineToWrite(line,lineNumber)){
                   case "Ignore":
                       break;
                   case "NotToWrite":
                       indexOfFaultyLine = lineNumber;
                       FaultyLine = line;
                       return null;
                   case "ToWrite":
                       builder.append(line).append("\n");
                       break;
               }
               lineNumber++;
            }
            return builder.toString();
        }catch (IOException e){
           e.printStackTrace();
        }
        return null;
    }

    protected boolean ValidateFile(){
        try{
            String line;
            int lineNumber = 0;
            BufferedReader BR = new BufferedReader(new FileReader(priviewdFile));
            StringBuilder builder = new StringBuilder();
            while((line = BR.readLine()) != null){
                switch (isLineToWrite(line,lineNumber)){
                    case "Ignore":
                        break;
                    case "NotToWrite":
                        indexOfFaultyLine = lineNumber;
                        FaultyLine = line;
                        return false;
                    case "ToWrite":
                        break;
                }
                lineNumber++;
            }
        return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    private String isLineToWrite(String line, int lineNumber){
        matcher = pattern.matcher(line);
        boolean isMatching = matcher.matches();
        if(IgnoreFirstLine && lineNumber == 0){
           return "Ignore";
        } else if (IgnoreEmptyLines && isLineEmpty(line)) {
            return "Ignore";
        } else if (IgnoreNotFittingLines && !isMatching) {
            return "Ignore";
        } else if (!isMatching){
            return "NotToWrite";
        } else {
            return "ToWrite";
        }
    }

    private boolean isLineEmpty(String line) {
        char [] table = line.toCharArray();
        for(char c : table){
            if(!Character.isWhitespace(c)) return false;
        }
        return true;
    }

    public void setIgnoreFirstLine(boolean selected){
        IgnoreFirstLine = selected;
    }

    public void setIgnoreEmptyLines(boolean selected){
        IgnoreEmptyLines = selected;
    }

    public void setIgnoreNotFittingLines(boolean selected){
        IgnoreNotFittingLines = selected;
    }

    public String getFaultyLine() {
        return FaultyLine;
    }

    public int getIndexOfFaultyLine() {
        return indexOfFaultyLine;
    }
}
