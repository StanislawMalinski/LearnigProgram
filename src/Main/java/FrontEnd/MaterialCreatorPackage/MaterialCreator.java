package FrontEnd.MaterialCreatorPackage;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MaterialCreator {
    private static String Name;
    private static boolean containsHint = false;
    private static boolean containsFormat = false;
    private static boolean containsAspects = false;

    private static boolean isLanguageCompatible = false;
    private static String Language;

    private static MaterialCreatorRunner MCR;

    public MaterialCreator(){
        MCR = new MaterialCreatorRunner(this);
    }

    public static String getName() {
        return Name;
    }

    public static boolean isContainsHint() {
        return containsHint;
    }

    public static boolean isContainsFormat() {
        return containsFormat;
    }

    public static boolean isContainsAspects() {
        return containsAspects;
    }

    public static boolean isLanguageCompatible() {
        return isLanguageCompatible;
    }

    public static String getLanguage() {
        return Language;
    }

    //TODO remove Stage as a argument from
    //- run(Stage stage)

    public void runBasicInfoWindow(){
        MCR.OpenBasicInfoWindow();
    }

    public void runCreatorWindow(){
        MCR.OpenCreatorWindow();
    }

    public static void setName(String Name){
        MaterialCreator.Name = Name;
    }

    public static void setContainsHint(boolean containsHint){
        MaterialCreator.containsHint = containsHint;
    }

    public static void setContainsFormat(boolean containsFormat) {
        MaterialCreator.containsFormat = containsFormat;
    }

    public static void setContainsAspects(boolean containsAspects) {
        MaterialCreator.containsAspects = containsAspects;
    }

    public static void setLanguageCompatible(boolean languageCompatible) {
        MaterialCreator.isLanguageCompatible = languageCompatible;
    }

    public static void setLanguage(String language) {
        MaterialCreator.Language = language;
    }
}
