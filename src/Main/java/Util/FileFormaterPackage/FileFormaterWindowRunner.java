package Util.FileFormaterPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileFormaterWindowRunner extends Application {

    private File file;

    public FileFormaterWindowRunner(){}

    @Override
    public void start(Stage stage){
        newFileFormater(stage);
    }

    public void run(String [] args){
        launch(args);
    }

    public void newFileFormater(Stage stage){
        try {
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

    public void setFile(File file){
        this.file = file;
    }
}
