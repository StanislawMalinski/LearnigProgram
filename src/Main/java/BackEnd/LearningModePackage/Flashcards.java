package BackEnd.LearningModePackage;

import BackEnd.Question;
import BackEnd.Teacher;
import Util.Information;
import Util.Option;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.EventListener;

public class Flashcards implements LearnMode{
    private Question question;
    private int questionIndex;
    private Label lable;
    private Pane flashcard;
    private boolean isQuestionShown;
    private int levelOfLearningProces;
    /*
0 - Not learned yet
1 - Shown once
n - revied n times
*/
    private static boolean learnByDefinition = true;

    public Flashcards(){}

    @Override
    public String getHint() {
        return null;
    }

    @Override
    public Pane getAnswerPlatform() {
        Pane ap = newEmptyAnswerPlatform();
        Pane fc = newEmptyFlashcard();
        ap.getChildren().add(fc);
        return ap;
    }

    private Pane newEmptyAnswerPlatform(){
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setPrefHeight(Information.HEIGHT_OF_ANSWER_PLATFORM_PANE);
        pane.setPrefWidth(Information.WIDTH_OF_ANSWER_PLATFORM_PANE);
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(220,220,220),null,null)));
        return pane;
    }

    private Pane newEmptyFlashcard(){
        Pane fc = new Pane();
        lable = new Label();
        fc.setLayoutX(Information.X_OF_FLASHCARD_PANE);
        fc.setLayoutY(Information.Y_OF_FLASHCARD_PANE);
        fc.setPrefWidth(Information.WIDTH_OF_FLASHCARD_PANE);
        fc.setPrefHeight(Information.HEIGHT_OF_FLASHCARD_PANE);
        fc.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
        flashcard = fc;
        flashcard.getChildren().add(lable);
        fc.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                double Mouse_x = dragEvent.getX();
                double Mouse_y = dragEvent.getY();
                ChangeListener listener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {

                    }
                };
            }
        });
        return fc;
    }

    @Override
    public void showQuestion(){
        String text;
        if( learnByDefinition) {
            text = GenerateLabelTableFor(question.getDefinition());
            isQuestionShown = false;
        }else {
            text = GenerateLabelTableFor(question.getQuestion());
            isQuestionShown = true;
        }
        lable.setText(text);
    }

    private String GenerateLabelTableFor(String string){
        lable.setLayoutX((flashcard.getPrefWidth() - 5*string.length())/2);
        lable.setLayoutY((flashcard.getPrefHeight() - 10)/2);
        return string;
    }


    public static void setAnswerByQuestionMode() {
        learnByDefinition = false;
    }

    public static void setAnswerByDefinitionMode() {
        learnByDefinition = true;
    }

    @Override
    public void setQuestion(Question question, int questionIndex) {
        if(question == null) throw new NullPointerException();
        this.question = question;
        this.questionIndex = questionIndex;
    }

    private void FlipFlashCard(){
        String text;
        if(isQuestionShown) {
            text = GenerateLabelTableFor(question.getDefinition());
            isQuestionShown = false;
        } else {
            text = GenerateLabelTableFor(question.getQuestion());
            isQuestionShown = true;
        }
        lable.setText(text);
    }


    @Override
    public EventHandler<KeyEvent> getHandler() {
        EventHandler<KeyEvent> listener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    FlipFlashCard();
                } else if (keyEvent.getCode() == KeyCode.D){
                    Teacher.getTeacher().addSucces();
                    Teacher.getTeacher().addAttempt();
                    Teacher.getTeacher().next();
                } else if (keyEvent.getCode() == KeyCode.A) {
                    Teacher.getTeacher().addAttempt();
                    Teacher.getTeacher().next();
                }
            }
        };
        return listener;
    }

    @Override
    public Option getSetting() {
        Option options = new Option();
        options.addOption("Answer by definition", a -> Flashcards.setAnswerByDefinitionMode());
        options.addOption("Answer by Question", a -> Flashcards.setAnswerByQuestionMode());
        return options;
    }

    @Override
    public void ShowEndScreen() {
        ;
    }
}
