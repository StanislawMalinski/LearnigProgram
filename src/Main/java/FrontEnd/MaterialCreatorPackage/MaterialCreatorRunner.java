package FrontEnd.MaterialCreatorPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class MaterialCreatorRunner{
    private MaterialCreator materialCreator;
    protected MaterialCreatorRunner(MaterialCreator materialCreator){
        this.materialCreator = materialCreator;
    }

    //TODO remove Stage as a argument from
    //- OpenCreatorWindow(Stage stage)
    //- OpenBasicInfoWindow(Stage stage)

    protected void OpenCreatorWindow(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontEnd/MaterialCreatorPackage/MaterialCreator.fxml"));
            Parent root = loader.load();
            MaterialCreatorController MCC = loader.getController();
            MCC.setName(MaterialCreator.getName());
            MCC.setContainsHint(MaterialCreator.isContainsHint());
            MCC.setContainsFormat(MaterialCreator.isContainsFormat());
            MCC.setContainsLanguageCompitable(MaterialCreator.isLanguageCompatible());
            MCC.setLanguage(MaterialCreator.getLanguage());
            MCC.setContainsAspects(MaterialCreator.isContainsAspects());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setIconified(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void OpenBasicInfoWindow(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontEnd/MaterialCreatorPackage/MaterialCreatorBasicInfo.fxml"));
            Parent root = loader.load();
            MaterialCreatorBasicInfoController MCBIC = loader.getController();
            MCBIC.setOnCreateSetAction(a -> OpenCreatorWindow(stage));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setIconified(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
