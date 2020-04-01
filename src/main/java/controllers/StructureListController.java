package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import objects.Structure;

public class StructureListController {

    private MainController mainController;

    @FXML
    private Button HubButton;
    @FXML
    private Button Structure1Button;

    public void init(MainController controller) {
        mainController = controller;
    }

    @FXML
    public void HubButtonClicked(ActionEvent event) {
        Structure structure = new Structure();
        structure.setImage("/assets/structures/hub.png");
        ImageView imageView = structure.getImageView();
        mainController.mainPane.getChildren().add(imageView);
        mainController.controlManager.placeStructure(structure);
    }
}
