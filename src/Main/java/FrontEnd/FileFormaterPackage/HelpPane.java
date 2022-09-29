package FrontEnd.FileFormaterPackage;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HelpPane extends Pane {
    private Label content = new Label("""
            If you are having trouble, using Set\s
            Generator Reformater tool, try our help
            in Help -> Set Generator Reformater.""");

    public HelpPane(double x, double y){
        this.setPrefHeight(50);
        this.setPrefWidth(220);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setOpacity(0.99);
        this.setVisible(false);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(230,230,230),new CornerRadii(0.4),null)));
        this.getChildren().add(content);
        this.setOnMouseExited(giveOnMouseExited());
        this.setOnMouseEntered(giveOnMouseClicked());
    }

    public EventHandler<MouseEvent> giveOnMouseClicked(){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(true);
            }
        };
    }

    public EventHandler<MouseEvent> giveOnMouseExited(){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(false);
            }
        };
    }
}
