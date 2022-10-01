package FrontEnd.FileFormaterPackage;

import Util.InvalidFileStructureException;

import java.io.File;
import java.util.regex.Pattern;

public class TextAlterator extends FileReaderFormatter{
    private StringBuilder builder;

    public TextAlterator(File file) {
        super(file);
    }

    public TextAlterator(File file, String pattern){
        super(file, pattern);
    }

    public TextAlterator(File file, Pattern pattern){
        super(file, pattern);
    }

    @Override
    protected void ActionIgnore(String line, int numberLine) {
        ;
    }

    @Override
    protected void ActionNotWritable(String line, int numberLine) {
        if(numberLine != 0 && !(numberLine == 1 && isIgnoreFirstLine()))
            builder.append("\n");
        builder.append(line);
    }

    @Override
    protected void ActionWritable(String line, int numberLine) {
        if(numberLine != 0 && !(numberLine == 1 && isIgnoreFirstLine()))
            builder.append("\n");
        builder.append(line);
    }

    public String getAlteredText(){
        builder = new StringBuilder();
        performAction();
        return builder.toString();
    }
}
