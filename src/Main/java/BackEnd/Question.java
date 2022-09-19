package BackEnd;

import java.util.Arrays;
import java.util.Objects;

public class Question implements Comparable<Question>{
    public String definition;
    public String answer;
    public int id;
    public TypeOfData type;
    public int attempts;
    public int successes;
    public String hint;
    public String format;
    public String [] aspects;
    public String [] nameOfAspects;
    public int numberOfAspects;

    public Question(){
        aspects = new String[0];
        nameOfAspects = new String[0];
        numberOfAspects = 0;
    }

    public void SizeUpAspects(){
        String [] Naspects = new String[numberOfAspects + 1];
        String [] NnameOfAspects = new String[numberOfAspects + 1];
        System.arraycopy(aspects,0,Naspects,0,numberOfAspects);
        System.arraycopy(nameOfAspects,0,NnameOfAspects,0,numberOfAspects);
        aspects = Naspects;
        nameOfAspects = NnameOfAspects;
        numberOfAspects ++;
    }

    @Override
    public boolean equals(Object o){
        Question question;
        try{
             question = (Question) o;
        } catch (ClassCastException e){
            return false;
        }
        if(!question.definition.equals(this.definition)) return false;
        if(!question.answer.equals(this.answer)) return false;
        if(this.hint != null && !question.hint.equals(this.hint)) return false;
        if(this.format != null && !question.format.equals(this.format)) return false;
        for(int i = 0; i < numberOfAspects; i++)
            if(this.aspects[i] != null && !question.aspects[i].equals(this.aspects[i])) {
                System.out.println("\"" + question.aspects[i] + "\" != \"" + this.aspects[i] + "\"");
                return false;
            }
        return true;
    }

    @Override
    public int compareTo(Question o) {
        return (this.attempts - o.attempts - this.successes + o.successes);
    }
}
