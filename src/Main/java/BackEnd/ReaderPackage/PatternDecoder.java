package BackEnd.ReaderPackage;

import BackEnd.Question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PatternDecoder {
    private Pattern pattern;
    private String line;

    private boolean hasHint = false;
    private boolean hasFormat = false;
    private int numberOfAspects = 0;

    private Question question;

    protected PatternDecoder(String line){
        this.line = line;
        getParameters();
        getReadingPattern();
        question = extrectDateAndCreateQustion();
    }

    private void getParameters(){
        if(line.contains("<hint>")) hasHint = true;
        if(line.contains("<format>")) hasFormat = true;
        for(int i = 1; true; i++){
            if(line.contains("<aspect" + i + ">")) numberOfAspects++;
            else break;
        }
    }

    private void getReadingPattern(){
        String StandardPattern = "\\$\\(<id>(?<id>\\d+)\\)\\$\\(<def>(?<def>.+)\\)\\$\\(<ans>(?<ans>.+)\\)\\$";
        String StatsPattern = "\\(<sequence>(?<sequence>.*)\\)\\$\\(<att>(?<att>\\d+)\\)\\$\\(<suc>(?<suc>\\d+)\\)\\$";
        String HintPattern = "(\\(<hint>(?<hint>.+)\\)\\$)";
        String FormatPattern = "(\\(<format>(?<format>.+)\\)\\$)";
        String AsspectsPattern = getAspectsPattern();
        String strPattern = "";
        strPattern += StandardPattern + StatsPattern;
        if(hasHint) strPattern += HintPattern;
        if(hasFormat) strPattern += FormatPattern;
        strPattern += AsspectsPattern;
        pattern =  Pattern.compile(strPattern);
    }

    private String getAspectsPattern() {
        String pattern = "";
        for(int i = 1; i <= numberOfAspects; i++){
            pattern += "\\(<aspect" + i + ">(?<aspect" + i + ">.+)\\)\\$";
        }
        if(numberOfAspects != 0)
            return pattern;
        else
            return "";
    }

    private Question extrectDateAndCreateQustion(){
        Question question = new Question();
        Matcher m = pattern.matcher(line);
        m.matches();
        question.id = Integer.parseInt(m.group("id"));
        question.definition = m.group("def");
        question.answer = m.group("ans");
        question.sequence = m.group("sequence");
        question.attempts = Integer.parseInt( m.group("att"));
        question.successes = Integer.parseInt( m.group("suc"));
        if(hasHint)
            question.hint = m.group("hint");
        if(hasFormat)
            question.format = m.group("format");
        for(int i = 0; i < numberOfAspects; i++) {
            question.SizeUpAspects();
            question.aspects[i] = m.group("aspect" + (i+1));
        }
        return question;
    }

    protected Question getQuestion(){
        return question;
    }
}
