package BackEnd;

import Util.QuestionList;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.io.File;

public class Material {
    private File file;
    private QuestionList questions;
    private Comparator<Question> comparator = new Comparator<Question>() {@Override public int compare(Question o1, Question o2) {return o1.compareTo(o2);}};
    private TypeOfData type;

    public Material(File file) {
        this.file = file;
        questions = new QuestionList();
    }

    public Material(QuestionList questionslist, TypeOfData type) {
        this.questions = questionslist;
        this.type = type;
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

}
