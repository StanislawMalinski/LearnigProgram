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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FileFormaterController implements Initializable {
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
    CheckBox CB_FLineAsATittle;

    private FileFormater fileFormater;

    public void CompilePattern(ActionEvent event){
        int reply = fileFormater.CompilePattern(TF_UserInput.getText());
        setComunicator(reply);
    }

    //\[\w\] \ans - \def

    public void SubmitPattern(ActionEvent event){
        fileFormater.setPattern(TF_UserInput.getText());
        fileFormater.getStandardReformattedFile();
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

    protected String getHeadFileIgnoringFirstLine(boolean b){
        String [] list = fileFormater.getHeadFromFile();
        if(b && list[2] != null)
            return list[1] + "\n"+ list[2];
        else if(b)
            return list[1];
        else
            return list[0] + "\n" + list[1];
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileFormater = new FileFormater(FileEditor.getFile());
        TA_FIlePreview.setText(getHeadFileIgnoringFirstLine(true));
        fileFormater.setFirstLineAsATittle(true);
        B_Submit.setDisable(true);
        CB_FLineAsATittle.setSelected(true);
        CB_FLineAsATittle.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                TA_FIlePreview.setText(getHeadFileIgnoringFirstLine(CB_FLineAsATittle.isSelected()));
                fileFormater.setFirstLineAsATittle(CB_FLineAsATittle.isSelected());
            }
        });
    }
}
