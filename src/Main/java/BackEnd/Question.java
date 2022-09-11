package BackEnd;

public class Question implements Comparable<Question>{
    private final String question;
    private final String answer;
    private TypeOfData type;
    private int attempts;
    private int successes;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer, TypeOfData type) {
        this.question = question;
        this.answer = answer;
        this.type = type;
    }

    public String getDefinition(){
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getAttempts(){
        return attempts;
    }

    public int getSuccesses(){
        return successes;
    }

    public TypeOfData getType(){
        return type;
    }

    public void addAttempt(){
        attempts++;
    }

    public void addSuccess(){
        successes++;
    }

    @Override
    public int compareTo(Question o) {
        return (this.getAttempts() - o.getAttempts() - this.getSuccesses() + o.getSuccesses());
    }
}
