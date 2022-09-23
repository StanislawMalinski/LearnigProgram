package FrontEnd.FileChooserPackage;

import Util.SubjectChooser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class FileChooserController implements Initializable {
    @FXML
    ListView<String> FileListView;
    @FXML
    Button SelectButton;
    @FXML
    Button CancelButton;

    private String[] Files;
    private String picked;
    private Consumer<String> cons;

    public void setSubjects(String [] subjects){
        this.Files = subjects;
        FileListView.getItems().addAll(Files);
    }

    public void onFileSelected(Consumer<String> cons){
        this.cons = cons;
    }

    public String choosed() {
        return picked;
    }

    public void Select(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if(picked != null)
            cons.accept("FileKeeper/Subjects/" + picked);
        picked = null;
        stage.close();
    }

    public void Cancle(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        picked = null;
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SelectButton.setDisable(true);
        FileListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                picked = FileListView.getSelectionModel().getSelectedItem();
                SelectButton.setDisable(picked == null);
            }
        });
    }
}
