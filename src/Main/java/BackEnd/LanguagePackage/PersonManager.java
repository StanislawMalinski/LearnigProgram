package BackEnd.LanguagePackage;

import BackEnd.LanguagePackage.GrammerPartPackage.Person;

public class PersonManager {
    private Person[] persons;
    private int cursor;

    public PersonManager(){
        persons = new Person[0];
        cursor = 0;
    }

    public void addPerson(String name, String person, String pluarity, String sex){
        Person person1 = new Person(name, person, pluarity, sex);
        sizeUp();
        persons[cursor++] = person1;
    }

    public Person getPerson(int n){
        return persons[n]; //TODO getPerson() with possibility of choosing person, by plurality and/or by person.
    }

    private void sizeUp(){
        Person [] npersons = new Person[cursor+1];
        System.arraycopy(persons,0,npersons,0,cursor);
        persons = npersons;
    }
}
