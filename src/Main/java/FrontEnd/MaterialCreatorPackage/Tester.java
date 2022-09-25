package FrontEnd.MaterialCreatorPackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class Tester extends Application {
    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MaterialCreator mC = new MaterialCreator();
        mC.runBasicInfoWindow(stage);
    }
}
