import FrontEnd.GUIrunner;
import FrontEnd.MainController;
import javafx.event.ActionEvent;

public class UserImitation {
    private MainController MC;
    public UserImitation() {
        Main.main(new String[0]);
        Main.getRunner().run(new String[0]);
        MC = GUIrunner.getMC();
    }

    public void stm(){;
    }
}


//--module-path /Users/stanislaw/Documents/Work/javafx-sdk-18.0.1/lib --add-modules javafx.controls,javafx.fxml