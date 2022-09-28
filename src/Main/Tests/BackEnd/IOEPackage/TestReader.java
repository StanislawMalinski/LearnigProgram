package BackEnd.IOEPackage;

import BackEnd.Material;
import BackEnd.Question;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestReader {
    private Reader reader;
    private File file;
    private Material material;
    private Question question0;
    private Question question1;
    private Question question2;
    private Question question3;
    private Question question4;
    private Question question5;
    private Question question6;
    private Question question7;

    @Before
    public void Initialize(){
        file = new File("src/Main/TestResources/WłoskiCoded.txt");
        material = new Material(file);
    }

    @Before
    public void IntitizalizeQuestions(){
        question0 = new Question();
        question1 = new Question();
        question2 = new Question();
        question3 = new Question();
        question4 = new Question();
        question5 = new Question();
        question6 = new Question();
        question7 = new Question();

        question0.definition = "Niebieski";
        question0.answer = "Blu";
        question0.successes = 0;
        question0.attempts = 0;

        question1.definition = "Zielony";
        question1.answer = "Verde";
        question1.successes = 0;
        question1.attempts = 0;
        question1.hint = "Kolor trawy";

        question2.definition = "Mieć";
        question2.answer = "Avere ho/hai/ha/abbiamo/avete/hanno";
        question2.successes = 0;
        question2.attempts = 0;
        question2.format = "czasownik";

        question3.definition = "Latać";
        question3.answer = "Volare";
        question3.successes = 0;
        question3.attempts = 0;
        question3.SizeUpAspects();
        question3.aspects[0] = "FRUUU";

        question4.definition = "Spadać";
        question4.answer = "Cadere";
        question4.successes = 0;
        question4.attempts = 0;
        question4.hint = "Możesz to zrobić ze schodów";
        question4.SizeUpAspects();
        question4.aspects[0] = "FRUUU";

        question5.definition = "Mówić";
        question5.answer = "Dire";
        question5.successes = 0;
        question5.attempts = 0;

        question6.definition = "Kobieta";
        question6.answer = "la Donna";
        question6.successes = 0;
        question6.attempts = 0;
        question6.hint = "Bardzo je lubię";
        question6.SizeUpAspects();
        question6.aspects[0] = "FRUUU";
        question6.SizeUpAspects();
        question6.aspects[1] = "Najlpesza i najgorsza rzecz na świecie";

        question7.definition = "Mężczyzna";
        question7.answer = "il Uomo";
        question7.successes = 0;
        question7.attempts = 0;
        question7.hint = "Bardzo ich lubię";
        question7.format = "rzeczownik";
        question7.SizeUpAspects();
        question7.aspects[0] = "FRUUU";
        question7.SizeUpAspects();
        question7.aspects[1] = "Najlpsza rzecz na świecie";
    }

    @Test
    public void TestReaderDefAns(){
        Question question0r = material.getQuestion(0);
        assertTrue(question0r.equals(question0));
    }

    @Test
    public void TestReaderDefAnsHint(){
        Question question1r = material.getQuestion(1);
        assertTrue(question1r.equals(question1));
    }

    @Test
    public void TestReaderDefAnsFormat(){
        Question question2r = material.getQuestion(2);
        assertTrue(question2r.equals(question2));
    }

    @Test
    public void TestReaderDefAnsAspect1(){
        Question question3r = material.getQuestion(3);
        assertTrue(question3r.equals(question3));
    }

    @Test
    public void TestReaderDefAnsHintAspect1(){
        Question question4r = material.getQuestion(4);
        assertTrue(question4r.equals(question4));
    }

    @Test
    public void TestReaderDefAns2(){
        Question question5r = material.getQuestion(5);
        assertTrue(question5r.equals(question5));
    }

    @Test
    public void TestReaderDefAnsHintAspect1Aspect2(){
        Question question6r = material.getQuestion(6);
        assertTrue(question6r.equals(question6));
    }

    @Test
    public void TestReaderDefAnsHintFormatAspect1Aspect2(){
        Question question7r = material.getQuestion(7);
        assertTrue(question7r.equals(question7));
    }

}
