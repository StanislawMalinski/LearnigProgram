package FrontEnd;

import Util.Option;

public class Settings {
    private static Option optionList;
    private Settings settings;


    public Settings(){
        if(settings == null){
            InitializeOption();
        }
    }

    private void InitializeOption(){
        optionList = new Option();
        optionList.addOption("Learning settings", a -> {});
        optionList.addOption("Change language", a -> {});
    }


}
