package FrontEnd.FileFormaterPackage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.net.URL;
import java.util.ResourceBundle;

public class FileFormaterController implements Initializable {

    @FXML
    Pane P_MainPane;

    @FXML
    TextArea TA_FIlePreview;

    @FXML
    TextField TF_ExceptionField;

    @FXML
    TextField TF_UserInput;

    @FXML
    Label L_Comunicator;

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

    private FileFormater fileFormater;
    private HelpPane pane;

    public void CompilePattern(ActionEvent event){
        fileFormater.setIgnoreEmptyLines(CB_IgnoreEmptyLines.isSelected());
        int reply = fileFormater.CompilePattern(TF_UserInput.getText());
        setComunicator(reply);
    }

    public void SubmitPattern(ActionEvent event){
        fileFormater.setPattern(TF_UserInput.getText());
        fileFormater.setIgnoreFirstLine(CB_IgnoreFirstLine.isSelected());
        fileFormater.setIgnoreEmptyLines(CB_IgnoreEmptyLines.isSelected());
        fileFormater.setIgnoreNptFittingLines(CB_IgnoreNotFittingLines.isSelected());
        fileFormater.getStandardReformattedFile();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void Cancel(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void setComunicator(int i){
        TF_ExceptionField.setText("");
        switch (i) {
            case 1 -> {L_Comunicator.setText("Invalid pattern. Try our help guide in menu Help -> Regular Expression.");
                B_Submit.setDisable(true);}
            case 2 -> {L_Comunicator.setText("Given pattern is not aplicable, try diffrent pattern.");
                B_Submit.setDisable(true);
                String FaultyLine = fileFormater.getFaultyLine();
                int FaultyLineIndex = fileFormater.getFaultyLineIndex();
                TF_ExceptionField.setText(FaultyLineIndex + ".  " + FaultyLine);}
            case 3 -> {L_Comunicator.setText("Compiled successfully.");
                B_Submit.setDisable(false);}
            case 4 -> {L_Comunicator.setText("Expression should contain \"\\def\" and \"\\ans\".");
                B_Submit.setDisable(false);}
        }
    }

    public void IgnoreFirstLine(ActionEvent e){
        fileFormater.setIgnoreFirstLine(CB_IgnoreFirstLine.isSelected());
        TA_FIlePreview.setText(fileFormater.getAlteredText());
    }

    public void IgnoreEmptyLines(ActionEvent e){
        fileFormater.setIgnoreFirstLine(CB_IgnoreFirstLine.isSelected());
        TA_FIlePreview.setText(fileFormater.getAlteredText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        L_Comunicator.setText("");
        fileFormater = new FileFormater(FileEditor.getFile());
        TA_FIlePreview.setText(fileFormater.getAlteredText());
        B_Submit.setDisable(true);
        CB_IgnoreFirstLine.setSelected(true);
        pane = new HelpPane(C_Help.getLayoutX(), C_Help.getLayoutY());
        C_Help.setOnMouseClicked(pane.giveOnMouseClicked());
        C_Help.setOnMouseExited(pane.giveOnMouseExited());
        P_MainPane.getChildren().add(pane);
    }
}
