package BackEnd.ReaderPackage;

import BackEnd.Material;
import BackEnd.Question;
import BackEnd.TypeOfData;
import Util.Information;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    private File file;
    private Material material;

    public Reader() {}

    public Reader(File file) {
        this.file = file;
    }

    public String[] getSubjects() {
        File file = new File(Information.locationOfTheSubjects);
        return file.list();
    }

    public Material read() {
        material = new Material(file);
        BufferedReader bufReader = getBufferdReader();
        try {
            readInformation(bufReader);
            readQuestions(bufReader);
        } catch(IOException er) {
            er.printStackTrace();
            return null;
        }
        return material;
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

    private void readInformation(BufferedReader bufferedReader) throws IOException {
        String firstLine = bufferedReader.readLine();
        if (firstLine.equalsIgnoreCase("language"))
            material.setType(TypeOfData.LANGUAGE);
        else if (firstLine.equalsIgnoreCase("flaschcards"))
            material.setType(TypeOfData.FLASHCARDS);
        else
            throw new InvalidPropertiesFormatException("First line is not type of data \"" + firstLine + "\"");
    }

    private void readQuestions(BufferedReader bufferedReader) throws IOException{
        String line;
        while((line = bufferedReader.readLine()) != null){
            readQuestionFromALine(line);
        }
    }

    private void readQuestionFromALine(String line){
        PatternDecoder patternDecoder = new PatternDecoder(line);
        Question question = patternDecoder.getQuestion();
        material.addQuestion(question);
    }
}
