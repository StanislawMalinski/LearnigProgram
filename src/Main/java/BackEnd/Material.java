package BackEnd;

import BackEnd.IOEPackage.Reader;
import BackEnd.Update.Updater;
import Util.QuestionList;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.io.File;

public class Material {
    private File file;
    private QuestionList questions;
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

    public Material() {
        questions = new QuestionList();
    }

    public Question [] getQuestions(int numberOfLessons){
        Question [] lessons = new Question[numberOfLessons];
        questions.SortByDifficulty(); // Sorting list
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

    public void updateMySelf(){
        questions.SortByID();
        Updater updater = new Updater(this);
        try {
            updater.generateNewFile(true);
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Error while updating, " + file.getName() + " file.");
        }
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
