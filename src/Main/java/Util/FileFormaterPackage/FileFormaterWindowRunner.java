package Util.FileFormaterPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

class FileFormaterWindowRunner{

    public FileFormaterWindowRunner() {}

    protected void run(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Util/FileFormaterPackage/FileFormaterWindow.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setIconified(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
