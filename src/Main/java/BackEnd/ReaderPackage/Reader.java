package BackEnd.ReaderPackage;

import BackEnd.Material;
import BackEnd.Question;
import BackEnd.TypeOfData;
import Util.Information;
import Util.QuestionList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    private File file;
    private List<Question> list;

    public Reader() {}

    public Reader(File file) {
        this.file = file;
        list = new QuestionList();
    }

    public String[] getSubjects() {
        File file = new File(Information.locationOfTheSubjects);
        return file.list();
    }

    public List<Question> read() {
        BufferedReader bufReader = getBufferdReader();
        try {
            readQuestions(bufReader);
        } catch(IOException er) {
            er.printStackTrace();
            return null;
        }
        return list;
    }

    public BufferedReader getBufferdReader(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader;
        } catch (IOException er){
            er.printStackTrace();
        }
        return null;
    }

    private void readQuestions(BufferedReader bufferedReader) throws IOException{
        String line;
        int lineNumber = 0;
        while((line = bufferedReader.readLine()) != null){
            if(lineNumber++ == 0) continue;
                readQuestionFromALine(line);
        }
    }

    private void readQuestionFromALine(String line){
        PatternDecoder patternDecoder = new PatternDecoder(line);
        Question question = patternDecoder.getQuestion();
        list.add(question);
    }
}
