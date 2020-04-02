package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import managers.AssetManager;
import managers.ControlManager;
import managers.ObjectManager;

public class MainController {

    public ControlManager controlManager;
    public AssetManager assetManager;
    public ObjectManager objectManager;
    @FXML public AnchorPane mainPane;
    @FXML public Node structureList;
    @FXML private StructureListController structureListController;

    @FXML private void click(ActionEvent event) {
        ;
    }

    @FXML public void initialize() {
        structureListController.init(this);
    }

    public void init(ControlManager cmanager, AssetManager amanager, ObjectManager omanager) {
        controlManager = cmanager;
        controlManager.initMainPane(mainPane);
        assetManager = amanager;
        objectManager = omanager;
    }

    public void tick() {
        ;
    }
}
