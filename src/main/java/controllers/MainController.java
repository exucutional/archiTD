package controllers;

import java.util.Iterator;
import java.util.Random;

import org.Settings;

import events.EventListener;
import events.EventType;
import javafx.application.Platform;
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
import objects.Spreader;
import objects.Structure;
import objects.Target;
import utility.Vector2D;

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

    private void initEvents() {
        eventManager.subscribe(EventType.SPAWN_ENEMY, new EventListener() {
            @Override
            public void update() {
                Target target = objectManager.getRandomTarget();
                Structure structure = new Spreader(objectManager, 256);
                Vector2D position = (target != null) 
                    ? target.getGlobalCenter() 
                    : new Vector2D(Settings.get().getWindowWidth() - 100, (Settings.get().getWindowHeight() - structure.getHeight()) / 2);
                Random random = new Random();
                structure.setImage(assetManager.getImage("structure-spreader"), true);
                structure.setParent(mainPane);
                mainPane.getChildren().add(structure.getView());
                objectManager.addStructure(structure);
                objectManager.addEnemy(structure);
                if (target != null) {
                    double radius = Settings.get().getPlaceRadius() / 2;
                    double x = - position.getX() - 1;
                    double y = - position.getY() - 1;
                    while (position.getX() + x > Settings.get().getWindowWidth()
                        || position.getX() + x < 0
                        || position.getY() + y > Settings.get().getWindowHeight()
                        || position.getY() + y < 0) {
                            x = (random.nextGaussian() - 0.8) * radius;
                            y = random.nextGaussian() * radius;
                    }
                    structure.setPosition(position.getX() + x, position.getY() + y);
                } else {
                    structure.setPosition(position);
                }
                structureListController.connectStructure(structure);
            }
        });
    }

    private void initHub() {
        Structure structure = new Structure(assetManager.getImage("structure-hub"), true);
        structure.setActive(false);
        structure.setParent(mainPane);
        mainPane.getChildren().add(structure.getView());
        objectManager.addStructure(structure);
        structure.setDurability(Settings.get().getDefaultDurability() * 5);
        structure.setPosition(structure.getWidth() / 2 - 50, (Settings.get().getWindowHeight() - structure.getHeight()) / 2);
    }

    public void init(ControlManager controlManager, AssetManager assetManager, ObjectManager objectManager, EventManager eventManager) {
        this.controlManager = controlManager;
        this.controlManager.init(mainPane, eventManager);
        this.assetManager = assetManager;
        this.objectManager = objectManager;
        this.eventManager = eventManager;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initEvents();
                initHub();
            }
        });

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
