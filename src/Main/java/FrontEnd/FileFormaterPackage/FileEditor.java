package FrontEnd.FileFormaterPackage;

import java.io.File;

public class FileEditor {
    private static File file;
    private static File reformatted;

    public FileEditor(File file) {
        FileEditor.file = file;
    }

    public FileEditor(String fileName) {
        file = new File(fileName);
    }

    protected static File getFile(){
        return file;
    }

    protected static void setReformatted(File file){
        reformatted = file;
    }

    public void getFileFormaterWindow(){
        FileFormaterWindowRunner runner = new FileFormaterWindowRunner();
        runner.run();
    }

    public File getReformattedFile(){
        return reformatted;
    }
}
