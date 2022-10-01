package FrontEnd.FileFormaterPackage;

import Util.InvalidFileStructureException;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.IllegalFormatException;
import java.util.regex.Pattern;

public class Compiler extends FileReaderFormatter{

    public Compiler(File file) {
        super(file);
    }

    public Compiler(File file, String SPattern){
        super(file, SPattern);
    }

    public Compiler(File file, Pattern pattern){
        super(file, pattern);
    }

    @Override
    protected void ActionIgnore(String line, int numberLine){
        ;
    }

    @Override
    protected void ActionNotWritable(String line, int numberLine) {
        setFaultyLine(line);
        setIndexOfFaultyLine(numberLine);
        throw new InvalidFileStructureException(numberLine + ". " + numberLine);
    }

    @Override
    protected void ActionWritable(String line, int numberLine) {
        ;
    }

    public boolean compile(){
        if(getPattern() == null) throw new IllegalArgumentException("Pattern is needed in order to compile.");
            try {
                performAction();
            } catch( InvalidFileStructureException e){
                return false;
            }
        return true;
    }
}