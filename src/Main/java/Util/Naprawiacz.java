package Util;

import java.io.*;

public class Naprawiacz {
    public static void main(String [] args){
        try {
            File file = new File("Languages/Italian/Words");
            File newfile = new File("Languages/Italian/NewWords");
            newfile.createNewFile();
            FileReader FR = new FileReader(file);
            FileWriter FW = new FileWriter(newfile);

            char lastChar = 'a';
            char currentChar;
            int currentInt;
            while( (currentInt = FR.read()) != -1){
                currentChar = (char) currentInt;
                if(Character.isAlphabetic(currentChar) && !Character.isDigit(lastChar)){
                    FW.write(currentChar);
                    lastChar = currentChar;
                } else if (Character.isAlphabetic(currentChar) && Character.isDigit(lastChar)){
                    FW.write("\n");
                    FW.write(currentChar);
                    lastChar = currentChar;
                }
            }
            FR.close();
            FW.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
