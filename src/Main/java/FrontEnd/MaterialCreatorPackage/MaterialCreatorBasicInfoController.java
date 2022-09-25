package FrontEnd.MaterialCreatorPackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class MaterialCreatorBasicInfoController implements Initializable {
    @FXML
    TextField TF_SetsName;

    @FXML
    CheckBox CB_ContainHint;

    @FXML
    CheckBox CB_ContainFormat;

    @FXML
    CheckBox CB_LanguageContent;

    @FXML
    CheckBox CB_ContainAspect;

    @FXML
    ChoiceBox<String> CB_LanguageChooser;

    @FXML
    Button B_CreateSet;

    @FXML
    Circle C_Help;

    @FXML
    Label L_Communicator;

    private final String [] Languages = { "","Polski", "Włoski", "Niemiecki"};

    private Consumer<Object> onCreateSetAction;

    public void ContainHint(ActionEvent e){

    }

    public void ContainFormat(ActionEvent e){
        CB_LanguageContent.setDisable(!CB_ContainFormat.isSelected());
        CB_ContainAspect.setDisable(!CB_ContainFormat.isSelected());
        if(!CB_ContainFormat.isSelected()){
            CB_LanguageContent.setSelected(false);
            CB_LanguageChooser.setValue("");
            CB_ContainAspect.setSelected(false);
        }
    }

    public void LanguageContent(ActionEvent e){
        CB_LanguageChooser.setDisable(!CB_LanguageContent.isSelected());
    }

    public void ContainAspect(ActionEvent e){

    }

    public void CreateSet(ActionEvent e){
        if(!ValidateSelection()) return;
        MaterialCreator.setName(TF_SetsName.getText() + ".txt");
        MaterialCreator.setContainsHint(CB_ContainHint.isSelected());
        MaterialCreator.setContainsFormat(CB_ContainFormat.isSelected());
        MaterialCreator.setLanguageCompatible(CB_LanguageContent.isSelected());
        MaterialCreator.setContainsAspects(CB_ContainAspect.isSelected());
        onCreateSetAction.accept(new Object());
    }

    private boolean ValidateSelection(){
        if(!checkNameValidity()){
            Communicate(1);
            return false;
        }
        if(CB_LanguageContent.isSelected() && CB_LanguageChooser.getValue().equals("")){
            Communicate(2);
            return false;
        }
        return true;
    }

    private boolean checkNameValidity(){
        String name = TF_SetsName.getText();
        char [] arr = name.toCharArray();
        for (char c : arr) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != '(' && c != ')') {
                return false;
            }
        }
        return true;
    }

    private void Communicate(int id){
        switch (id) {
            case 1 -> L_Communicator.setText("Use only alphabetic and number characters.");
            case 2 -> L_Communicator.setText("Choose language.");
        }
    }

    public void setOnCreateSetAction(Consumer<Object> consumer){
        onCreateSetAction = consumer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB_LanguageChooser.getItems().addAll(Languages);
        CB_LanguageContent.setDisable(true);
        CB_LanguageChooser.setDisable(true);
        CB_ContainAspect.setDisable(true);
    }

}
