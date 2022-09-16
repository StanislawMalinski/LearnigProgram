package BackEnd.LanguagePackage;

public class GrammerManager {
    private PersonManager personManager;
    private TensesManager tensesManager;

    public GrammerManager(){
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
