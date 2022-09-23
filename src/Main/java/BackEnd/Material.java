package BackEnd;

import BackEnd.ReaderPackage.Reader;

import java.util.Comparator;
import java.util.Iterator;
import java.io.File;
import java.util.List;

public class Material {
    private File file;
    private List<Question> questions;
    private Comparator<Question> comparator = new Comparator<Question>() {
        @Override
        public int compare(Question o1, Question o2) {
            return  o1.compareTo(o2);
        }
    };
    private TypeOfData type;
    private int timeInSeconds;
    private int StartTime;

    public Material(File file) {
        Reader reader = new Reader(file);
        this.file = file;
        questions = reader.read();
    }

    public Material(String filePath) {
        file = new File(filePath);
        Reader reader = new Reader(file);
        this.file = file;
        questions = reader.read();
    }

    public Question [] getQuestions(int numberOfLessons){
        Question [] lessons = new Question[numberOfLessons];
        questions.listIterator(); // Sorting list
        System.arraycopy(questions.toArray(),0,lessons,0,numberOfLessons);
        return lessons;
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

    public Question getQuestion(int index){
        if(index >= 0 && index < questions.size())
            return questions.get(index);
        return null;
    }

    public int getIndexOfQuestionWithId(int id){
        Question question = new Question();
        question.id = id;
        return questions.indexOf(question);
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

    public void showQuestions() {
        Iterator<Question> iter = questions.iterator();
        Question question;
        while(iter.hasNext()) {
            question = iter.next();
            System.out.println("id: " + question.id + "|attempts: " + question.attempts + "|success: " + question.successes);
        }
    }
}
