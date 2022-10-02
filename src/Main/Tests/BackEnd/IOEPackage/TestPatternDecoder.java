package BackEnd.IOEPackage;

import BackEnd.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPatternDecoder {
    private static PatternDecoder decoder1;
    private static PatternDecoder decoder2;
    private static PatternDecoder decoder3;
    private static PatternDecoder decoder4;
    private static PatternDecoder decoder5;

    @BeforeAll
    public static void SetUp(){
        decoder1 = new PatternDecoder("$(<id>0)$(<def>Niebieski)$(<ans>Blu)$(<sequence>as)$(<att>1)$(<suc>1)$");
        decoder2 = new PatternDecoder("$(<id>1)$(<def>Zielony)$(<ans>Verde)$(<sequence>ass)$(<att>1)$(<suc>2)$(<hint>Kolor trawy)$");
        decoder3 = new PatternDecoder("$(<id>2)$(<def>Mieć)$(<ans>Avere ho/hai/ha/abbiamo/avete/hanno)$(<sequence>)$(<att>0)$(<suc>0)$(<format>czasownik)$");
        decoder4 = new PatternDecoder("$(<id>3)$(<def>Latać)$(<ans>Volare)$(<sequence>)$(<att>0)$(<suc>0)$(<aspect1>FRUUU)$");
        decoder5 = new PatternDecoder("$(<id>7)$(<def>Mężczyzna)$(<ans>il Uomo)$(<sequence>)$(<att>0)$(<suc>0)$(<aspect1>FRUUU)$(<aspect2>Najlpsza rzecz na świecie)$");
    }

    @Test
    public void TestMinimum(){
        Question questionActual = decoder1.getQuestion();
        Question questionExpected = new Question(0,"Niebieski", "Blu","as",1,1);

        assertEquals(questionExpected,questionActual);
    }

    @Test
    public void TestHint(){
        Question questionActual = decoder2.getQuestion();
        Question questionExpected = new Question(1,"Zielony", "Verde","ass",1,2);
        questionExpected.hint = "Kolor trawy";

        assertEquals(questionExpected,questionActual);
    }

    @Test
    public void TestFormat(){
        Question questionActual = decoder3.getQuestion();
        Question questionExpected = new Question(2,"Mieć", "Avere ho/hai/ha/abbiamo/avete/hanno","",0,0);
        questionExpected.format = "czasownik";

        assertEquals(questionExpected,questionActual);
    }

    @Test
    public void TestAspecet1(){
        Question questionActual = decoder4.getQuestion();
        Question questionExpected = new Question(3,"Latać", "Volare","",0,0);
        questionExpected.SizeUpAspects();
        questionExpected.aspects[0] = "FRUUU";

        assertEquals(questionExpected,questionActual);
    }

    @Test
    public void TestAspect1And2(){
        Question questionActual = decoder5.getQuestion();
        Question questionExpected = new Question(7,"Mężczyzna", "il Uomo","",0,0);
        questionExpected.SizeUpAspects();
        questionExpected.SizeUpAspects();
        questionExpected.aspects[0] = "FRUUU";
        questionExpected.aspects[1] = "Najlpsza rzecz na świecie";

        assertEquals(questionExpected,questionActual);
    }

}