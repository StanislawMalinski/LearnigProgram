package FrontEnd.FileFormaterPackage;

import Util.Information;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCreator {
    private File file;
    private String fileName;
    private String path;
    private static boolean isVersioning;

    private final Pattern pattern = Pattern.compile(".*\\.txt");

    public FileCreator(String fileName){
        this.fileName = fileName;
    }

    public void setPath(String path){
        this.path = path;
    }

    public boolean ValidateFilename(){
        Matcher m = pattern.matcher(fileName);
        if(!m.matches()) {
            if (fileName.contains(".")) {
                return false;
            }
            this.fileName = fileName + ".txt";
        }
        String pathAndFile;
        if(path != null){
            pathAndFile = path + fileName;
        }else{
            pathAndFile = Information.locationOfTheSubjects + fileName;
        }
        if(isVersioning) return true;
        file = new File(pathAndFile);
        if(file.exists()){
            return false;
        }
        return true;
    }

    public boolean CreateFile(){
        Matcher m = pattern.matcher(fileName);
        if(!m.matches()) {
            if (fileName.contains(".")){
                throw new InvalidParameterException("Filename must not contain dots, if its not for extension \".txt\".");
            }
            this.fileName = fileName + ".txt";
        }
        String pathAndFile;
        if(path != null){
            pathAndFile = path + fileName;
        }else{
            pathAndFile = Information.locationOfTheSubjects + fileName;
        }
        file = new File(pathAndFile);
        if(!isVersioning) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        File newFile;
        String newFileName = pathAndFile;
        int version = 1;
        try {
            while(true) {
                newFile = new File(newFileName);
                if (newFile.createNewFile()) {
                    file = newFile;
                    return true;
                } else
                    newFileName = pathAndFile.replace(".txt", "(" + version + ").txt");
                    version++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteFile(){
        if(file != null){
            file.delete();
        }
    }

    public File getFile() {
        return file;
    }

    public static void setVersioning(boolean selected){
        isVersioning = selected;
    }

    public boolean isVersioning() {
        return isVersioning;
    }
}