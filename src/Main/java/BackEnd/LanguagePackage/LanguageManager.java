package BackEnd.LanguagePackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LanguageManager {
    private GrammerMaster grammerMaster;

    public LanguageManager(){
        grammerMaster = new GrammerMaster();
    }

    public void LoadGrammer(File file){
        try {
            BufferedReader BR = new BufferedReader(new FileReader(file));
            String line;
            while((line = BR.readLine()) != null){
                read(line);
            }
        } catch (IOException e){
            throw new IllegalArgumentException("File " + file.getName() + " has a incorrect structure.");
        }
    }

    public void read(String line){
        String [] arrBuff = line.split("$");
        String typeOfDate = arrBuff[0];
        switch (typeOfDate){
            case "Person/":
                String person = arrBuff[1];
                String plurality = arrBuff[2];
                String sex = arrBuff[3];
                String personName = arrBuff[4];
                grammerMaster.addPerson(personName, person, plurality, sex);
                break;
            case "Tense/":
                String tenseName = arrBuff[1];
                String hintWhenToUse = arrBuff[2];
                String grammer = arrBuff[3];
                break;
            case "Verb/":
                break;
            case "Adjective":
                break;
            case "Noun":
                break;
            default:
                throw new IllegalArgumentException("Unknown part of speech \"" + typeOfDate + "\".");
        }
    }
}
