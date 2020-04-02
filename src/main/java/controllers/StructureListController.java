package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import objects.Structure;

public class StructureListController {

    private MainController mainController;

    @FXML private Button HubButton;
    @FXML private Button TurretButton;

    public void init(MainController controller) {
        mainController = controller;
    }

    @FXML public void HubButtonClicked(ActionEvent event) {
        Structure structure = new Structure();
        structure.setImage(mainController.assetManager.getImage("structure-hub"));
        ImageView imageView = structure.getImageView();
        mainController.mainPane.getChildren().add(imageView);
        mainController.controlManager.placeStructure(structure);
    }

    @FXML public void TurretButtonClicked(ActionEvent event) {
        ;
    }
}
