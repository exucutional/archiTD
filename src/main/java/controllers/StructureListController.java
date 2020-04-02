package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import objects.Structure;
import objects.Turret;

public class StructureListController {

    private MainController mainController;

    @FXML private Button HubButton;
    @FXML private Button TurretButton;

    public void init(MainController controller) {
        mainController = controller;
    }

    @FXML public void hubButtonClicked(ActionEvent event) {
        Structure structure = new Structure(mainController.assetManager.getImage("structure-hub"));
        mainController.mainPane.getChildren().add(structure.getImageView());
        mainController.controlManager.placeStructure(structure);
    }

    @FXML public void turretButtonClicked(ActionEvent event) {
        Structure tower = new Structure(mainController.assetManager.getImage("structure-tower-simple"));
        Turret turret = new Turret(tower);
        turret.setImage(mainController.assetManager.getImage("structure-turret-simple"));
        turret.setLocalCenter(16, 16);
        turret.setActive(false);
        mainController.mainPane.getChildren().add(tower.getImageView());
        mainController.mainPane.getChildren().add(turret.getImageView());
        mainController.controlManager.placeStructure(turret);
        mainController.objectManager.addStructure(turret);
    }
}
