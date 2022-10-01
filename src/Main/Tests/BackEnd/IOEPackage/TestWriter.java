package BackEnd.IOEPackage;

import BackEnd.Material;
import BackEnd.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestWriter {
    private static Material material;
    private static File stream;

    @BeforeAll
    public static void Initiazlize(){
        material = new Material();
        stream = new File("tmp.txt");
        try{
            stream.createNewFile();
        }catch (IOException e){
            fail();
        }
        Question question;
        for( int i = 0; i < 3; i++){
            question = new Question();
            question.id = i;
            question.definition = i + "" + i + "def";
            question.answer = i + "ans";
            question.sequence = "ass";
            question.attempts = i;
            question.successes = i;
            question.hint = i + "" + i + "" + i + "hint";
            question.format = "format" + i;
            for(int j = 0; j < i; j++) {
                question.SizeUpAspects();
                question.nameOfAspects[j] = "Aspect number" + i;
                question.aspects[j] = "Aspect " + (i * i);
            }
            material.addQuestion(question);
        }
    }

    @Test
    public void Test(){
        BufferedReader BR = null;
        Writer writer = new Writer(stream);
        writer.setMaterial(material);
        try {
            writer.write();
            BR = new BufferedReader(new FileReader(stream));
            LocalDate myObj = LocalDate.now();
            String expc0 = "$(<lastLearned>" + myObj +")$(<time>0:0)$";
            String expc1 = "$(<id>0)$(<def>00def)$(<ans>0ans)$(<sequence>ass)$(<att>0)$(<suc>0)$(<hint>000hint)$(<format>format0)$";
            String expc2 = "$(<id>1)$(<def>11def)$(<ans>1ans)$(<sequence>ass)$(<att>1)$(<suc>1)$(<hint>111hint)$(<format>format1)$(<aspect0>Aspect 1)$";
            String expc3 = "$(<id>2)$(<def>22def)$(<ans>2ans)$(<sequence>ass)$(<att>2)$(<suc>2)$(<hint>222hint)$(<format>format2)$(<aspect0>Aspect 4)$(<aspect1>Aspect 4)$";

            String line0 = BR.readLine();
            String line1 = BR.readLine();
            String line2 = BR.readLine();
            String line3 = BR.readLine();

            BR.close();

            assertTrue(line0.equals(expc0));
            assertTrue(line1.equals(expc1));
            assertTrue(line2.equals(expc2));
            assertTrue(line3.equals(expc3));
        }catch (IOException e){
            fail();
        }
    }

    @AfterAll
    public static void Clear(){
        stream.delete();
    }
}
