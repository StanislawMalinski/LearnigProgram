package BackEnd.LanguagePackage;

import java.io.File;

public class LanguageManager {
    private GrammerManager grammerMaster;

    public LanguageManager(){
        grammerMaster = new GrammerManager();
    }

    public void LoadGrammer(File file){
        LanguageReader grammerReader = new LanguageReader(file);
    }

}
