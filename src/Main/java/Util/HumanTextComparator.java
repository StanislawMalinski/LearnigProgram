package Util;

public abstract class HumanTextComparator {
    private static String correct;
    private static String HumanText;
    private static int numberOfPossibleMistake;

    public static boolean CompareWithLevel(String correct, String HumanText, int level, int numberOfPossibleMistake){ //TODO replace int level with Enumeration class
        setParameters(correct,HumanText,numberOfPossibleMistake);
        switch (level){
            case 0: //Very strict
                return Compare();
            case 1: //Ignore size of the letter
                toLowerCase();
                return Compare();
            case 2: //Ignore white characters
                removeWhiteCharacters();
                return Compare();
            case 3: //Ignore size of the letter and white characters
                toLowerCase();
                removeWhiteCharacters();
                return Compare();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    private static void setParameters(String correct, String HumanText, int numberOfPossibleMistake){
        HumanTextComparator.correct = correct;
        HumanTextComparator.HumanText = HumanText;
        HumanTextComparator.numberOfPossibleMistake = numberOfPossibleMistake;
    }

    private static boolean Compare(){
        char [] correctArr = correct.toCharArray();
        char [] humanArr = HumanText.toCharArray();

        int mistakeCounter = 0;
        if(correctArr.length != humanArr.length) return false;

        for (int i = 0; i < correctArr.length; i++){
            if( correctArr[i] != humanArr[i]){
                mistakeCounter++;
                if(mistakeCounter > numberOfPossibleMistake) return false;
            }
        }
        return true;
    }

    private static void toLowerCase(){
        correct = correct.toLowerCase();
        HumanText = HumanText.toLowerCase();
    }

    private static void removeWhiteCharacters(){
        correct = correct.replace(" ","");
        HumanText = HumanText.replace(" ", "");
    }
}
