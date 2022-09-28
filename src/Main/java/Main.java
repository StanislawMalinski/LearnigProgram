import FrontEnd.GUIrunner;

public class Main {
    private static GUIrunner guirunner;
    public static void main(String[] args) {
        guirunner = new GUIrunner();
        guirunner.run(args);
    }

    protected static GUIrunner getRunner(){
        return guirunner;
    }
    //--module-path /Users/stanislaw/Documents/Work/javafx-sdk-18.0.1/lib --add-modules javafx.controls,javafx.fxml
}
