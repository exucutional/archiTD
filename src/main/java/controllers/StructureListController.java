package controllers;

import java.util.Iterator;

import org.Settings;

import events.EventListener;
import events.EventType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import objects.Connection;
import objects.Connector;
import objects.Repeller;
import objects.Spreader;
import objects.Structure;
import objects.Turret;
import utility.Vector2D;

public class StructureListController {

    private MainController mainController;

    @FXML private Button HubButton;
    @FXML private Button TurretButton;
    @FXML private Button SpreaderButton;
    @FXML private Button RepellerButton;
    @FXML private Button ConnectorButton;

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
        Turret turret = new Turret(tower, mainController.objectManager);
        turret.setImage(mainController.assetManager.getImage("structure-turret-simple"), true);
        turret.setLocalCenter(16, 16);
        turret.setActive(false);
        turret.setTarget(mainController.controlManager.mouseTarget);
        mainController.eventManager.subscribe(EventType.SHOOT, new EventListener() {
            @Override
            public void update() {
                turret.shoot();
            }
        });
        mainController.mainPane.getChildren().add(tower.getView());
        mainController.mainPane.getChildren().add(turret.getView());
        mainController.controlManager.placeStructure(turret);
        mainController.objectManager.addStructure(turret);
        mainController.objectManager.addDefence(turret);
    }

    @FXML public void spreaderButtonClicked(ActionEvent event) {
        Structure structure = new Spreader(mainController.objectManager, 512);
        structure.setImage(mainController.assetManager.getImage("structure-tower-simple"), true);
        structure.setActive(false);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
        mainController.objectManager.addStructure(structure);
        mainController.objectManager.addEnemy(structure);
    }

    @FXML public void repellerButtonClicked(ActionEvent event) {
        Repeller structure = new Repeller();
        structure.setActive(false);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
        mainController.objectManager.addStructure(structure);
        mainController.objectManager.addForceObject(structure);
    }

    @FXML public void connectorButtonClicked(ActionEvent event) {
        Connector connector = new Connector();
        connector.setImage(mainController.assetManager.getImage("structure-tower-simple"), true);
        connector.setActive(false);
        mainController.mainPane.getChildren().add(connector.getView());
        mainController.controlManager.placeStructure(connector);
        mainController.objectManager.addStructure(connector);
        mainController.eventManager.subscribe(EventType.PLACE, new EventListener(){
        
            @Override
            public void update() {
                Iterator<Structure> iter = mainController.objectManager.getStructureIterator();
                while (iter.hasNext()) {
                    Structure structure = iter.next();
                    Vector2D begin = structure.getGlobalCenter();
                    Vector2D end = connector.getGlobalCenter();
                    if (begin.sub(end).magnitude() < Settings.get().getPlaceRadius()) {
                        Connection connection = new Connection(structure.getGlobalCenter(), connector.getGlobalCenter());
                        mainController.mainPane.getChildren().add(connection.getView());
                    }
                }
            }
        });
    }

}
