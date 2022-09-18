package Util.FileFormaterPackage;

import javafx.stage.Stage;

import java.io.File;

public class FileEditor {
    private static File file;

    public FileEditor(File file) {
        FileEditor.file = file;
    }

    public FileEditor(String fileName) {
        file = new File(fileName);
    }

    protected static File getFile(){
        return file;
    }

    public void getFileFormaterWindow(Stage stage){
        FileFormaterWindowRunner runner = new FileFormaterWindowRunner();
        runner.setFile(file);
        runner.start(stage);
    }
}
