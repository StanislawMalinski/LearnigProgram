package FrontEnd.FileFormaterPackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.naming.InsufficientResourcesException;
import java.io.File;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

public class FileFormatterController implements Initializable {

    @FXML
    TextField TF_SetsName;

    @FXML
    Pane P_MainPane;

    @FXML
    TextArea TA_FilePreview;

    @FXML
    TextField TF_ExceptionField;

    @FXML
    TextField TF_UserInput;

    @FXML
    Label L_Communicator;

    @FXML
    Button B_Compile;

    @FXML
    Button B_Submit;

    @FXML
    Button B_CancelButton;

    @FXML
    CheckBox CB_IgnoreFirstLine;

    @FXML
    CheckBox CB_IgnoreEmptyLines;

    @FXML
    CheckBox CB_IgnoreNotFittingLines;

    @FXML
    Circle C_Help;

    @FXML
    CheckBox CB_SetVersioning;

    private HelpPane pane;
    private String FaultyLine;
    private int indexOfFaultyLine;

    public void CompilePattern(ActionEvent event) {
        Compiler compiler = null;
        try {
            compiler = new Compiler(FileEditor.getFile(), TF_UserInput.getText());
            updateFRF(compiler);
        } catch (InvalidParameterException e){
            setCommunicator(4);
            return;
        }
        if(!compiler.compile()){
            TF_ExceptionField.setText(compiler.getIndexOfFaultyLine() + ".  " + compiler.getFaultyLine());
            FaultyLine = compiler.getFaultyLine();
            indexOfFaultyLine = compiler.getIndexOfFaultyLine();
            setCommunicator(2);
        }else{
            B_Submit.setDisable(false);
            TF_ExceptionField.setText("");
            setCommunicator(3);
        }
    }

    private void setCommunicator(int i){
        TF_ExceptionField.setText("");
        switch (i) {
            case 0 -> {
                L_Communicator.setText("");
                B_Submit.setDisable(true);}
            case 1 -> {
                L_Communicator.setText("Invalid pattern. Try our help guide in menu Help -> Regular Expression.");
                B_Submit.setDisable(true);}
            case 2 -> {
                L_Communicator.setText("Given pattern is not applicable, try different pattern.");
                B_Submit.setDisable(true);
                TF_ExceptionField.setText(indexOfFaultyLine + ".  " + FaultyLine);}
            case 3 -> {
                L_Communicator.setText("Compiled successfully.");
                B_Submit.setDisable(false);}
            case 4 -> {
                L_Communicator.setText("Expression should contain \"\\def\" and \"\\ans\".");
                B_Submit.setDisable(true);}
            case 5 -> {L_Communicator.setText("Invalid name or the name given is already taken.");
                B_Submit.setDisable(true);}
            case 6 -> {
                L_Communicator.setText("Filename must not contain dots, if its not for extension \".txt\".");
                B_Submit.setDisable(true);}
        }
    }

    public void SubmitPattern(ActionEvent event){
        FileFormatingWriter writer = new FileFormatingWriter(FileEditor.getFile(),TF_UserInput.getText());
        updateFRF(writer);
        FileCreator creator;
        try {
            creator = new FileCreator(TF_SetsName.getText());
        } catch (InvalidParameterException e){
            setCommunicator(6);
            return;
        }
        creator.CreateFile();
        File outputStream = creator.getFile();
        writer.setOutputStream(outputStream);
        try {
            writer.write();
        } catch (InsufficientResourcesException e){
            ;
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void updateFRF(FileReaderFormatter FRF){
        FRF.setPattern(TF_UserInput.getText());
        FRF.setIgnoreFirstLine(CB_IgnoreFirstLine.isSelected());
        FRF.setIgnoreEmptyLines(CB_IgnoreEmptyLines.isSelected());
        FRF.setIgnoreNotFittingLines(CB_IgnoreNotFittingLines.isSelected());
    }

    public void Cancel(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void IgnoreFirstLine(ActionEvent e){
        TextAlterator alterator = new TextAlterator(FileEditor.getFile(),TF_UserInput.getText());
        updateFRF(alterator);
        TA_FilePreview.setText(alterator.getAlteredText());
        setCommunicator(0);
    }

    public void IgnoreEmptyLines(ActionEvent e){
        TextAlterator alterator = new TextAlterator(FileEditor.getFile(),TF_UserInput.getText());
        updateFRF(alterator);
        TA_FilePreview.setText(alterator.getAlteredText());
        setCommunicator(0);
    }

    public void IgnoreNotFittingLines(ActionEvent e){
        TextAlterator alterator = new TextAlterator(FileEditor.getFile(),TF_UserInput.getText());
        updateFRF(alterator);
        TA_FilePreview.setText(alterator.getAlteredText());
        setCommunicator(0);
    }

    public void SetVersioning(ActionEvent e){
        FileCreator.setVersioning(CB_SetVersioning.isSelected());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        L_Communicator.setText("");
        TA_FilePreview.setText(FileEditor.getText());
        B_Submit.setDisable(true);
        pane = new HelpPane(C_Help.getLayoutX(), C_Help.getLayoutY());
        C_Help.setOnMouseClicked(pane.giveOnMouseClicked());
        C_Help.setOnMouseExited(pane.giveOnMouseExited());
        P_MainPane.getChildren().add(pane);
        TF_SetsName.setText(FileEditor.getFile().getName());
    }
}
