package FrontEnd.MaterialCreatorPackage;

import BackEnd.Material;
import BackEnd.Question;
import Util.Information;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MaterialCreatorController implements Initializable {
    @FXML
    TextField TF_Definition;

    @FXML
    TextField TF_Answer;

    @FXML
    Label L_Hint;

    @FXML
    TextField TF_Hint;

    @FXML
    Label L_Format;

    @FXML
    TextField TF_Format;

    @FXML
    TextField TF_Search;

    @FXML
    Button B_Add;

    @FXML
    Button B_Complete;

    @FXML
    Pane P_QuestionDisplay;

    @FXML
    ScrollPane SP_Display;

    private boolean containsHint = false;
    private QuestionInMaterialCreator [] table;
    private int cursorTable;
    private String setsName;

    public void setName(String name) {
        setsName = name;
    }

    public void setContainsHint(boolean containsHint) {
        if( containsHint ){
            this.containsHint = true;
            TF_Hint.setVisible(true);
            L_Hint.setVisible(true);
            QuestionInMaterialCreator.setContainsHint(true);
        }
    }

    public void setContainsFormat(boolean containsFormat) {
        if(containsFormat){
            TF_Format.setVisible(true);
            L_Format.setVisible(true);
        }
    }

    public void setContainsLanguageCompitable(boolean languageCompatible) {

    }

    public void setLanguage(String language) {

    }

    public void setContainsAspects(boolean containsAspects) {

    }

    public void Complete(ActionEvent e){
        Material material = new Material();
        Question question;
        for(int i = 0; i < cursorTable; i++){
            question = new Question();
            question.id = i;
            question.definition = table[i].getDefinition();
            question.answer = table[i].getAnswer();
            if(containsHint)
                question.hint = table[i].getHint();
            material.addQuestion(question);
        }
        File file = new File(Information.locationOfTheSubjects + setsName);
        try {
            file.createNewFile();
        }catch (IOException er){
            er.printStackTrace();
        }
        material.setFile(file);
        material.updateMySelf();

        Stage stage = (Stage) ((Node) e.getSource() ).getScene().getWindow();
        stage.close();
    }

    public void addQuestion(ActionEvent e){
        String Definition = TF_Definition.getText();
        String Answer = TF_Answer.getText();
        String Hint = TF_Hint.getText();

        TF_Definition.setText("");
        TF_Answer.setText("");
        TF_Hint.setText("");

        if(isValidQuestion(Definition, Answer)){
            addQuestion(Definition,Answer,Hint);
        }
    }

    private void addQuestion(String Definition, String Answer, String Hint){
        QuestionInMaterialCreator question = createQuestion(Definition, Answer, Hint);
        updateScrollPane();
        addToTableAndPane(question);
    }

    private boolean isValidQuestion(String Definition, String Answer){
        return !Definition.equals("") && !Answer.equals("");
    }

    private QuestionInMaterialCreator createQuestion(String Definition, String Answer, String Hint){
        QuestionInMaterialCreator question = new QuestionInMaterialCreator(cursorTable,Definition,Answer);
        if(containsHint && !Hint.equals(""))
            question.setHint(Hint);
        question.setLayoutX(0);
        question.setLayoutY(cursorTable*30);
        question.setGray0orWhite1(cursorTable%2);
        question.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TF_Definition.setText(question.getDefinition());
                TF_Answer.setText(question.getAnswer());
                TF_Hint.setText(question.getHint());
                question.setVisible(false);
                remove(question.getID());
            }
        });
        return question;
    }

    private void updateScrollPane(){
        if(cursorTable >= 7){
            P_QuestionDisplay.setPrefHeight(30*cursorTable);
            SP_Display.setContent(P_QuestionDisplay);
            SP_Display.setFitToWidth(true);
        }
    }

    private void addToTableAndPane(QuestionInMaterialCreator question){
        P_QuestionDisplay.getChildren().add(question);
        sizeUpTable();
        table[cursorTable++] = question;
    }

    private void sizeUpTable(){
        QuestionInMaterialCreator [] nTable = new QuestionInMaterialCreator[table.length+1];
        System.arraycopy(table,0,nTable,0,table.length);
        table = nTable;
    }

    private void sizeDownTable(){
        QuestionInMaterialCreator [] nTable = new QuestionInMaterialCreator[table.length-1];
        System.arraycopy(table,0,nTable,0,table.length-1);
        table = nTable;
        cursorTable--;
    }

    private void remove(int index){
        if(index < cursorTable)
            for(int i = index; i < cursorTable -1 ; i++){
                table[i] = table[i+1];
                table[i].updateID(i);
                table[i].setGray0orWhite1(i%2);
                table[i].setLayoutY(30*i);
            }
        if(cursorTable >= 7){
            P_QuestionDisplay.setPrefHeight(30*(cursorTable-1));
            SP_Display.setContent(P_QuestionDisplay);
            SP_Display.setFitToWidth(true);
        }
        sizeDownTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table = new QuestionInMaterialCreator[0];
        cursorTable = 0;
        this.containsHint = false;
        TF_Hint.setVisible(false);
        L_Hint.setVisible(false);
        TF_Format.setVisible(false);
        L_Format.setVisible(false);
    }
}
