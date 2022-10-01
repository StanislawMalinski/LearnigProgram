package FrontEnd.FileFormaterPackage;

import Util.Information;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private File file;
    private String fileName;
    private String path;

    public FileCreator(String fileName){
    this.fileName = fileName;
    }

    public void setPath(String path){
        this.path = path;
    }

    public boolean ValidateFilename(){
        String pathAndFile;
        if(path != null){
            pathAndFile = path + fileName;
        }else{
            pathAndFile = Information.locationOfTheSubjects + fileName;
        }
        file = new File(pathAndFile);
        if(file.exists()){
            return false;
        }
        return true;
    }

    public boolean CreateFile(){
        String pathAndFile;
        if(path != null){
            pathAndFile = path + fileName;
        }else{
            pathAndFile = Information.locationOfTheSubjects + fileName;
        }
        file = new File(pathAndFile);
        if(file.exists()){
            return false;
        }
        try {
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public File getFile(){
        return file;
    }

    public boolean CreateFileWithVersionIfExists(){
        String pathAndFile;
        if(path != null){
            pathAndFile = path + fileName;
        }else{
            pathAndFile = Information.locationOfTheSubjects + fileName;
        }
        File newFile;
        String fileName = pathAndFile;
        String newFileName;
        int version = 1;
        try {
            while(true) {
                newFileName = fileName.replace(".txt", "(" + version + ").txt");
                newFile = new File(newFileName);
                if (newFile.createNewFile()) {
                    file = newFile;
                    return true;
                } else
                    version++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}