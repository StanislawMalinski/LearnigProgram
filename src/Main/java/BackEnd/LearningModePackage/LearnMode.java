package BackEnd.LearningModePackage;

import BackEnd.Question;
import Util.Option;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public interface LearnMode {
    public String getHint();

    public Pane getAnswerPlatform();

    public void showQuestion();

    public void setQuestion(Question question);

    public EventHandler<KeyEvent> getHandler();

    public Option getSetting();

    public void ShowEndScreen();
}