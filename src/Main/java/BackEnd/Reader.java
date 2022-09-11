package BackEnd;

import Util.Information;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

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
        int counter = 1;
        while((line = bufferedReader.readLine()) != null){
            readQuestionFromALine(line, counter);
            counter++;
        }
    }

    private void readQuestionFromALine(String line, int lineIndex){
        switch(material.getType()){
            case LANGUAGE:
                return;
            case FLASHCARDS:
                line = line.replaceAll(" ","");
                String [] tab = line.split("-");
                if( tab.length > 2)
                    System.out.println("Incorrect text format in " + lineIndex + " line.\n\"" + line + "\"");
                Question question = new Question(tab[0], tab[1], TypeOfData.FLASHCARDS);
                material.addQuestion(question);
                return;
            default:
                return;
        }
    }

    public boolean analyze(){
        return true; //TODO write analyzeing method that will check if given document has valid foramt for extracting material.
    }
}
