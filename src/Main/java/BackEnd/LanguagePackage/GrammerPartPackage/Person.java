package BackEnd.LanguagePackage.GrammerPartPackage;

public class Person {
    public final String name;
    public final String plurality;
    public final String sex;
    public final String person;

    public Person(String name, String person, String plurality, String sex) {
        this.name = name;
        this.plurality = plurality;
        this.sex = sex;
        this.person = person;
    }
}
