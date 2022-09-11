package BackEnd;

import BackEnd.LearningModePackage.LearnMode;
import BackEnd.LearningModePackage.LearningMode;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Teacher {
    private static Material material;
    private static LearnMode learnMode;
    private static int numberOfQuestion;
    private static int cursor;
    private static Teacher teacher;

    public Teacher(Material t) {
        if (teacher == null) {
            material = t;
            numberOfQuestion = material.getSize();
            cursor = 0;
            teacher = this;
        }
    }

    public static Teacher getTeacher(){
        return teacher;
    }

    public void setTeachingTool(LearningMode learningMode){
        Teacher.learnMode = learningMode.getMode(learningMode);
    }

    public Pane getPlatformAnswer(){
        return learnMode.getAnswerPlatform();
    }

    public EventHandler<KeyEvent> getHandler(){
        return learnMode.getHandler();
    }

    public void teach(){
        nextQuestion();
    }

    public void next(){
        nextQuestion();
    }

    public void addAttempt(){
        material.getQuestion(cursor).addAttempt();
    }

    public void addSucces(){
        material.getQuestion(cursor).addSuccess();
    }

    private void nextQuestion() {
        learnMode.setQuestion(material.getQuestion(cursor), cursor);
        learnMode.showQuestion();
        cursor++;
    }
}
