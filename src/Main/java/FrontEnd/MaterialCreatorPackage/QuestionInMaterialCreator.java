package FrontEnd.MaterialCreatorPackage;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class QuestionInMaterialCreator extends Pane {
    private Label Definition;
    private Label Answer;
    private Label Hint;

    private int id;

    private static boolean containsHint = false;

    public QuestionInMaterialCreator(int id, String definition, String answer){
        this.id = id;

        Definition = new Label();
        Answer = new Label();
        Hint = new Label();
        Definition.setText(definition);
        Answer.setText(answer);

        setPrefWidth(560);
        setPrefHeight(30);

        Hint.setVisible(false);

        getChildren().addAll(Definition, Answer, Hint);

        Definition.setLayoutX(14);
        Definition.setLayoutY(8);

        Answer.setLayoutX(288);
        Answer.setLayoutY(8);

        if(containsHint){
            Answer.setLayoutX(266.6666);
        }

        setVisible(true);
    }

    public static void setContainsHint(boolean ContainsHint){
        containsHint = ContainsHint;
    }

    public void setHint(String hint){
        Hint.setText(hint);
        Hint.setLayoutX(453.3333);
        Hint.setLayoutY(8);
        Hint.setVisible(true);
    }

    public void setOnMouseClick(EventHandler<MouseEvent> handler){
        setOnMouseClicked(handler);
    }

    public String getDefinition(){
        return Definition.getText();
    }

    public String getAnswer(){
        return Answer.getText();
    }

    public String getHint(){
        return Hint.getText();
    }

    public void setGray0orWhite1(int color){
        if(color == 0){
            this.setStyle("-fx-background-color: #e5e5e5");
        }
        if(color == 1){
            this.setStyle("-fx-background-color: #ffffff");
        }
    }

    public void updateID(int id){
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
