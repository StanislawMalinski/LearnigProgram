package BackEnd;

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
    public String sequence = "";

    public void addAttempt(){
        sequence += "a";
        attempts++;
    }

    public void addSuccess(){
        sequence += "s";
        successes++;
    }

    public Question(){
        aspects = new String[0];
        nameOfAspects = new String[0];
        numberOfAspects = 0;
    }

    public Question(int id, String definition, String answer, String sequence, int attempts, int successes) {
        this.definition = definition;
        this.answer = answer;
        this.id = id;
        this.attempts = attempts;
        this.successes = successes;
        this.sequence = sequence;
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
    public String toString(){
        return "id: " + id + "|def: " + definition + "|attempts/succes" + attempts + "/" + successes;
    }

    @Override
    public int compareTo(Question o) {
        return (this.attempts - o.attempts - this.successes + o.successes);
    }
}
