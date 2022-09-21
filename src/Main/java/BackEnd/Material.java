package BackEnd;

import BackEnd.ReaderPackage.Reader;
import Util.QuestionList;

import java.sql.Time;
import java.util.Comparator;
import java.util.Iterator;
import java.io.File;
import java.util.List;

public class Material {
    private File file;
    private List<Question> questions;
    private Comparator<Question> comparator = new Comparator<Question>() {@Override public int compare(Question o1, Question o2) {return o1.compareTo(o2);}};
    private TypeOfData type;
    private int timeInSeconds;
    private int StartTime;

    public Material(File file) {
        Reader reader = new Reader(file);
        this.file = file;
        questions = reader.read();
    }

    public void setType(TypeOfData type){
        this.type = type;
    }

    public TypeOfData getType(){
        return type;
    }

    public Iterator<Question> Iterator(){
        return questions.iterator();
    }

    public void addQuestion(Question question){
        if( !questions.contains(question))
            questions.add(question);
    }

    public Question getQuestion(int id){
        if(id >= 0 && id < questions.size())
            return questions.get(id);
        return null;
    }

    public int getSize(){
        return questions.size();
    }

    public int [] getHardestQuestion(int n){
        questions.sort(comparator);
        int [] table = new int [n];
        return table;
    }

    public File getFile(){
        return file;
    }

    public void setFile(File file){
        this.file = file;
    }

    public void startTime(){
        StartTime = Math.round(Math.round(System.currentTimeMillis()));
    }

    public void stopTime(){
        int StopTime = Math.round(Math.round(System.currentTimeMillis()));
        int delta = StopTime - StartTime;
        timeInSeconds += Math.round(delta/1000);
    }

    public int getTime() {
        return timeInSeconds;
    }
}
