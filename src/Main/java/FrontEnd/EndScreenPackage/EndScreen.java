package FrontEnd.EndScreenPackage;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class EndScreen extends Pane {
    private Label label;

    public EndScreen(int attempts, int success){
        this.setPrefHeight(770);
        this.setPrefWidth(700);
    }

}
