package controllers;

import java.util.Iterator;

import org.Settings;

import events.EventListener;
import events.EventType;
import javafx.application.Platform;
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

    private void placeEvent(Structure placeStructure) {
        mainController.eventManager.subscribe(EventType.PLACE, new EventListener() {
            @Override
            public void update() {
                Iterator<Structure> iter = mainController.objectManager.getStructureIterator();
                while (iter.hasNext()) {
                    Structure structure = iter.next();
                    Vector2D begin = structure.getGlobalCenter();
                    Vector2D end = placeStructure.getGlobalCenter();
                    if (begin.sub(end).magnitude() < Settings.get().getPlaceRadius()) {
                        Connection connection = new Connection(structure.getGlobalCenter(), placeStructure.getGlobalCenter());
                        connection.setParent(mainController.mainPane);
                        structure.addConnection(connection);
                        placeStructure.addConnection(connection);
                        mainController.mainPane.getChildren().add(connection.getView());
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                mainController.objectManager.addStructure(connection);
                            }
                        });
                    }
                }
            }
        });
    }

    public void init(MainController controller) {
        mainController = controller;
    }

    @FXML public void hubButtonClicked(ActionEvent event) {
        Structure structure = new Structure(mainController.assetManager.getImage("structure-hub"), true);
        structure.setParent(mainController.mainPane);
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
        turret.setParent(mainController.mainPane);
        tower.setParent(mainController.mainPane);
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
        mainController.objectManager.addStructure(tower);
        mainController.objectManager.addDefence(turret);
        placeEvent(tower);
    }

    @FXML public void spreaderButtonClicked(ActionEvent event) {
        Structure structure = new Spreader(mainController.objectManager, 512);
        structure.setImage(mainController.assetManager.getImage("structure-tower-simple"), true);
        structure.setActive(false);
        structure.setParent(mainController.mainPane);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
        mainController.objectManager.addStructure(structure);
        mainController.objectManager.addEnemy(structure);
    }

    @FXML public void repellerButtonClicked(ActionEvent event) {
        Repeller structure = new Repeller();
        structure.setActive(false);
        structure.setParent(mainController.mainPane);
        mainController.mainPane.getChildren().add(structure.getView());
        mainController.controlManager.placeStructure(structure);
        mainController.objectManager.addStructure(structure);
        mainController.objectManager.addForceObject(structure);
    }

    @FXML public void connectorButtonClicked(ActionEvent event) {
        Connector connector = new Connector();
        connector.setImage(mainController.assetManager.getImage("structure-tower-simple"), true);
        connector.setActive(false);
        connector.setParent(mainController.mainPane);
        mainController.mainPane.getChildren().add(connector.getView());
        mainController.controlManager.placeStructure(connector);
        mainController.objectManager.addStructure(connector);
        placeEvent(connector);
    }

}
