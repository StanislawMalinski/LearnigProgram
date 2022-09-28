package FrontEnd;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIrunner extends Application {
    private static MainController MC;
    private static Stage stage;

    public GUIrunner(){}

    public static Stage getStage(){
        return stage;
    }

    public void run(String [] args){
        launch(args);
    }

    public static MainController getMC(){
        return MC;
    }

    @Override
    public void start(Stage stage) {
        Main(stage);
    }

    public void Main(Stage stage){
        GUIrunner.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
            Parent root = loader.load();
            MC = loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            stage.close();
        }
    }
}
