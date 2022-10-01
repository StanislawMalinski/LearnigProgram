package BackEnd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMaterial {
    private Teacher teacher;
    private static Material material;

    @BeforeAll
    public static void Initialize(){
        material = new Material("src/Main/TestResources/WłoskiCoded.txt");
    }

    @Test
    public void TestLesson(){
        material.getQuestion(5).addAttempt();
        material.getQuestion(5).addAttempt();
        material.getQuestion(5).addAttempt();
        material.getQuestion(5).addAttempt();

        material.getQuestion(4).addAttempt();
        material.getQuestion(4).addAttempt();

        material.getQuestion(3).addSuccess();
        material.getQuestion(3).addSuccess();
        material.getQuestion(3).addAttempt();
        material.getQuestion(3).addAttempt();
        material.getQuestion(3).addAttempt();

        material.getQuestion(2).addAttempt();
        material.getQuestion(2).addAttempt();
        material.getQuestion(2).addAttempt();

        material.getQuestion(1).addAttempt();

        Question question0 = material.getQuestion(5);
        Question question1 = material.getQuestion(2);
        Question question2 = material.getQuestion(4);

        Question [] lesson =  material.getQuestions(3);

        assertEquals(lesson[0], question0);
        assertEquals(lesson[1], question1);
        assertEquals(lesson[2], question2);
    }


}
