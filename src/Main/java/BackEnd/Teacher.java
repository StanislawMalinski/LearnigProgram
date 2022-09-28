package BackEnd;

import BackEnd.LearningModePackage.LearnMode;
import BackEnd.LearningModePackage.LearningMode;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class Teacher {
    private static Material material;
    private static LearnMode learnMode;
    private static Question [] lesson;
    private static int numberOfQuestionInLesson = 10;
    private static int numberOfQuestion;
    private static int cursor;
    private static Teacher teacher;

    private static int successInLesson;
    private static int attemptsInLesson;
    private static Consumer<Object> ConsumerForResetingKeyHandler;

    public Teacher(Material t) {
        if (teacher == null) {
            material = t;
            numberOfQuestion = material.getSize();
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

    public void startLesson(){
        material.startTime();
        newLesson();
        cursor = 0;
    }

    private void newLesson(){
        if(material.getSize() < numberOfQuestionInLesson)
            lesson = material.getQuestions(material.getSize());
        else
            lesson = material.getQuestions(numberOfQuestionInLesson);
        attemptsInLesson = 0;
        successInLesson = 0;
    }

    public void next(){
        learnMode.setQuestion(lesson[cursor++]);
        learnMode.showQuestion();
    }

    public static boolean hasNext() {
        return cursor < lesson.length;
    }

    public static void finishLesson() {
        material.stopTime();
        material.updateMySelf();
        ConsumerForResetingKeyHandler.accept(null);
    }

    public void addAttempt(){
        lesson[cursor].addAttempt();
        attemptsInLesson++;
    }

    public void addSucces(){
        lesson[cursor].addSuccess();
        successInLesson++;
    }

    public Question[] getNewLesson() {
        return material.getQuestions(numberOfQuestionInLesson);
    }

    public void setNumberOfQuestionInLesson(int n) {
        numberOfQuestionInLesson = n;
    }

    public void setResetKeyHandlar(Consumer<Object> consumer) {
        ConsumerForResetingKeyHandler = consumer;
    }
}