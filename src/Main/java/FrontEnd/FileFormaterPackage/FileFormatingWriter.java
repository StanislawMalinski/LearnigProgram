package FrontEnd.FileFormaterPackage;

import Util.InvalidFileStructureException;

import javax.naming.InsufficientResourcesException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFormatingWriter extends FileReaderFormatter{
    private File stream;
    private BufferedWriter BR;
    private Matcher m;
    private int lineCounter = 0;

    public FileFormatingWriter(File file) {
        super(file);
    }

    public FileFormatingWriter(File file, Pattern pattern) {
        super(file, pattern);
    }

    public FileFormatingWriter(File file, String SPattern) {
        super(file, SPattern);
    }

    @Override
    protected void ActionIgnore(String line, int numberLine) {
        ;
    }

    @Override
    protected void ActionNotWritable(String line, int numberLine) {
        throw new InvalidFileStructureException(numberLine + ".  " + line);
    }

    @Override
    protected void ActionWritable(String line, int numberLine) {
        String formatedLine = formatLine(line);
        try {
            BR.write(formatedLine);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String formatLine(String line) {
        StringBuilder newLine = new StringBuilder();
        m = getPattern().matcher(line);
        m.matches();
        newLine.append("$(<id>").append(lineCounter++).append(")$");
        newLine.append("(<def>").append(m.group("def")).append(")$");
        newLine.append("(<ans>").append(m.group("ans")).append(")$");
        newLine.append("(<sequence>)$");
        newLine.append("(<att>0)$");
        newLine.append("(<suc>0)$");
        if(line.contains("?<hint>")) newLine.append("(<hint>").append(m.group("hint")).append(")$");
        if(line.contains("?<format>")) newLine.append("(<format>").append(m.group("format")).append(")$");
        int i = 0;
        while(line.contains("?<aspect" + i + ">")){
            if(line.contains("<aspect" + i + ">"))
                newLine.append("(<aspect" + i + ">").append(m.group("aspect" + i)).append(")$");
            else
                break;
            i++;
        }
        newLine.append("\n");
        return newLine.toString();
    }

    protected void setOutputStream(File stream){
        this.stream = stream;
    }

    private void getBufferedReader(){
        try{
            BR = new BufferedWriter(new FileWriter(stream));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void write() throws InsufficientResourcesException {
        if( stream == null) throw new InsufficientResourcesException("Set output stream, before trying to write.");
        getBufferedReader();
        performAction();
        try {
            BR.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
