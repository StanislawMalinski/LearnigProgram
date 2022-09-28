package FrontEnd.HelpWindowPackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpWindowController implements Initializable{
    @FXML
    TreeView<String> TW_treeView;

    @FXML
    ScrollPane SP_ScrollPane;

    @FXML
    TextFlow TF_HelpDisplay;

    public void displayApplicationOverview(){
        Text text1 = new Text("\nApplication Overview\n");
        text1.setFill(Color.BLACK);
        text1.setX(10);
        text1.setY(10);
        text1.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        Text text2 = new Text(   "Welcome to our application, we are very happy to see you and we are " +
                                    "excited about this journey. We will walk you through basics of this " +
                                    "program.");
        TF_HelpDisplay.getChildren().addAll(text1, text2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Help");

        rootItem.setExpanded(true);

        TreeItem<String> Help1 = new TreeItem<>("Application overview");
        TreeItem<String> Help2 = new TreeItem<>("Set Creator");
        TreeItem<String> Help3 = new TreeItem<>("Automatize Set Creator");
        TreeItem<String> Help4 = new TreeItem<>("Language content");
        TreeItem<String> Help5 = new TreeItem<>("Format");
        TreeItem<String> Help6 = new TreeItem<>("Information");
        TreeItem<String> Help7 = new TreeItem<>("Program support");
        TreeItem<String> Help8 = new TreeItem<>("Support us");

        rootItem.getChildren().addAll(Help1,Help2,Help3,Help4,Help5,Help6,Help7,Help8);

        SP_ScrollPane.setFitToWidth(true);
        TW_treeView.setRoot(rootItem);
        TF_HelpDisplay.setTextAlignment(TextAlignment.JUSTIFY);
        TF_HelpDisplay.setMaxWidth(360);
    }

    public void selectItem() {
        TreeItem<String> item = TW_treeView.getSelectionModel().getSelectedItem();;

        TF_HelpDisplay.getChildren().clear();

        if(item != null) {
            switch(item.getValue()){
                case "Application overview":
                    displayApplicationOverview();
            }
        }
    }
}