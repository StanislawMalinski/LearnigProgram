package FrontEnd;

import BackEnd.LearningModePackage.LearningMode;
import BackEnd.Material;
import BackEnd.Teacher;
import FrontEnd.FileFormaterPackage.FileEditor;
import FrontEnd.MaterialCreatorPackage.MaterialCreator;
import Util.FileMaintenance;
import Util.SubjectChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    MenuItem MenuHelpButton;
    @FXML
    MenuItem FileButton;
    @FXML
    MenuItem AddFileButton;
    @FXML
    MenuItem RemoveFIleButton;
    @FXML
    MenuItem EditFileButton;
    @FXML
    MenuItem AddProblemButton;
    @FXML
    MenuItem FileReformater;
    @FXML
    MenuItem SetCreator;
    @FXML
    MenuItem SettingButton;
    @FXML
    MenuItem StatsButton;
    @FXML
    MenuItem PickTheSubjectButton;
    @FXML
    MenuItem SetLearningModeButton;
    @FXML
    MenuItem FlashcardsButton;
    @FXML
    MenuItem MeteoritesButton;
    @FXML
    MenuItem ExamButton;
    @FXML
    MenuItem WritingButton;
    @FXML
    MenuItem LearnModeButton;

    @FXML
    Pane AnswerPlatfromPane;

    private static Material material;
    private static Stage stage;
    private static Teacher teacher;

    public void MenuHelp(ActionEvent e) {
    }

    public void AddFile(ActionEvent e) {
        FileMaintenance fileMaintenance = new FileMaintenance();
        fileMaintenance.addFile(stage);
    }

    public void RemoveFile(ActionEvent e) {
        FileMaintenance fileMaintenance = new FileMaintenance();
        fileMaintenance.removeFile();
    }

    public void EditFile(ActionEvent e) { //TODO FileEditor
    }

    public void AddProblem(ActionEvent e) {
    }

    public void FileReformater(ActionEvent e){
        File choosed = getFileChooserWithTitle("Choose file to reformat.");
        FileEditor FD = new FileEditor(choosed);
        FD.getFileFormaterWindow();
    }

    public void CreateNewSet(ActionEvent e){
        MaterialCreator materialCreator = new MaterialCreator();
        materialCreator.runBasicInfoWindow();
    }

    public File getFileChooserWithTitle(String title){
        FileChooser FC = new FileChooser();
        FC.setTitle(title);
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        return FC.showOpenDialog(stage);
    }

    public void Setting(ActionEvent e) {
    }

    public void Stats(ActionEvent e) {
    }

    public void PickTheSubject(ActionEvent e) {
        SubjectChooser subjectChooser = new SubjectChooser();
        subjectChooser.onSelect(this::readMaterial);
    }

    private void readMaterial(String fileName){
        File file = new File(fileName);
        material = new Material(file);
        SetLearningModeButton.setDisable(material == null);
    }

    public void Flashcards(ActionEvent e) {
        setLearnMode(LearningMode.FLASHCARDS);
    }

    public void Meteorites(ActionEvent e) {
        setLearnMode(LearningMode.METORITES);
    }

    public void Exam(ActionEvent e) {
        setLearnMode(LearningMode.EXAME);
    }

    public void Writing(ActionEvent e) {
        setLearnMode(LearningMode.WRITING);
    }

    public void LearnMode(ActionEvent e) {
        setLearnMode(LearningMode.LEARN_MODE);
    }

    private void setLearnMode(LearningMode learningMode){
        teacher = new Teacher(material);
        teacher.setTeachingTool(learningMode);
        AnswerPlatfromPane.getChildren().add(teacher.getPlatformAnswer());
        teacher.startLesson();
        stage.getScene().setOnKeyPressed(teacher.getHandler());
        teacher.setResetKeyHandlar(a -> resetHandler());
    }

    public void resetHandler(){
        stage.getScene().setOnKeyPressed(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = GUIrunner.getStage();
        AnswerPlatfromPane.setBackground(new Background(new BackgroundFill(Color.BLUE,null,null)));
    }
}
