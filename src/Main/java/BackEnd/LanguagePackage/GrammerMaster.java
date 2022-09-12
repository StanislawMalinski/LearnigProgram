package BackEnd.LanguagePackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GrammerMaster {
    private PersonManager personManager;
    private TensesManager tensesManager;

    public GrammerMaster(){
        personManager = new PersonManager();
        tensesManager = new TensesManager();
    }

    public void addPerson(String name, String person, String plurality, String sex){
        personManager.addPerson(name, person, plurality, sex);
    }

    public void addTense(){
        tensesManager.addTense();
    }
}
