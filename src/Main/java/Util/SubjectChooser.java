package Util;

import BackEnd.IOEPackage.Reader;
import FrontEnd.FileChooserPackage.FileChooserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class SubjectChooser {
    private static File file;
    private static FileChooserController FCC;

    public SubjectChooser(){
        Reader reader = new Reader();
        String[] subjects = reader.getSubjects();
        fileChooserPopupWindow(subjects);
    }

    public void fileChooserPopupWindow(String [] options){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FrontEnd/FileChooserPackage/FileChooser.fxml"));
            Parent root = fxmlLoader.load();
            FCC = fxmlLoader.getController();
            FCC.setSubjects(options);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setIconified(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onSelect(Consumer<String> cons){
        FCC.onFileSelected(cons);
    }

    public File getFile(){
        return file;
    }
}
