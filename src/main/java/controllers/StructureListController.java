package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import objects.Repeller;
import objects.Spreader;
import objects.Structure;
import objects.Turret;

public class StructureListController {

    private MainController mainController;

    @FXML private Button HubButton;
    @FXML private Button TurretButton;
    @FXML private Button SpreaderButton;
    @FXML private Button RepellerButton;

    public void init(MainController controller) {
        mainController = controller;
    }

    @FXML public void hubButtonClicked(ActionEvent event) {
        Structure structure = new Structure(mainController.assetManager.getImage("structure-hub"), true);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
    }

    @FXML public void turretButtonClicked(ActionEvent event) {
        Structure tower = new Structure(mainController.assetManager.getImage("structure-tower-simple"), true);
        Turret turret = new Turret(tower);
        turret.setImage(mainController.assetManager.getImage("structure-turret-simple"), true);
        turret.setLocalCenter(16, 16);
        turret.setActive(false);
        turret.setTarget(mainController.controlManager.mouseTarget);
        mainController.mainPane.getChildren().add(tower.getView());
        mainController.mainPane.getChildren().add(turret.getView());
        mainController.controlManager.placeStructure(turret);
        mainController.objectManager.addStructure(turret);
    }

    @FXML public void spreaderButtonClicked(ActionEvent event) {
        Structure structure = new Spreader(mainController.objectManager, 240);
        structure.setImage(mainController.assetManager.getImage("structure-tower-simple"), true);
        structure.setActive(false);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
        mainController.objectManager.addStructure(structure);
    }

    @FXML public void repellerButtonClicked(ActionEvent event) {
        Repeller structure = new Repeller();
        structure.setActive(false);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
        mainController.objectManager.addStructure(structure);
        mainController.objectManager.addForceObject(structure);
    }
}
