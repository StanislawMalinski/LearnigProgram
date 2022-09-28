package Util;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

public class FileMaintenance {
    public FileMaintenance(){
    }

    public void addFile(Stage stage){
        File selectedFile = getFileFromFileChooser(stage);

        File dest = new File(Information.locationOfTheSubjects + selectedFile.getName());
        try {
            Files.copy(selectedFile.toPath(), dest.toPath());
        } catch (Exception E){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Already Exists Error");
            alert.setContentText("The file that you have chosen is already in the system. Change the name of your new" +
                    " file or delete old one.");
            alert.showAndWait();
        }
    }

    public void removeFile(){
        SubjectChooser sc = new SubjectChooser();
        sc.onSelect(s -> {
            File file = new File(s);
            file.delete();
        });
    }

    public File getFileFromFileChooser(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        return fileChooser.showOpenDialog(stage);
    }


}
