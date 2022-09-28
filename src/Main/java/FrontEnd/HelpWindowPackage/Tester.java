package FrontEnd.HelpWindowPackage;

import FrontEnd.MaterialCreatorPackage.MaterialCreatorBasicInfoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Tester extends Application {
    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontEnd/HelpWindowPackage/HelpWindow.fxml"));
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
