package controllers;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import managers.AssetManager;
import managers.ControlManager;
import managers.EventManager;
import managers.ObjectManager;
import objects.Gas;

public class MainController {

    public ControlManager controlManager;
    public AssetManager assetManager;
    public ObjectManager objectManager;
    public EventManager eventManager;
    @FXML public AnchorPane mainPane;
    @FXML public Canvas mainCanvas;
    @FXML public Node structureList;
    @FXML private StructureListController structureListController;

    @FXML private void click(ActionEvent event) {
        ;
    }

    @FXML public void initialize() {
        structureListController.init(this);
    }

    public void init(ControlManager controlManager, AssetManager assetManager, ObjectManager objectManager, EventManager eventManager) {
        this.controlManager = controlManager;
        this.controlManager.init(mainPane, eventManager);
        this.assetManager = assetManager;
        this.objectManager = objectManager;
        this.eventManager = eventManager;
    }

    public void render() {
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        Iterator<Gas> iter = objectManager.getGasIterator();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        while (iter.hasNext()) {
            Gas gas = iter.next();
            gas.setImage(assetManager.getImage(String.format("entity-gas-%d", (int) Math.floor(gas.getLifespan()))), false);
        }
        objectManager.render(gc);
    }
}
