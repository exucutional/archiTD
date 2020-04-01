package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import managers.ControlManager;

public class MainController {

    public ControlManager controlManager;
    @FXML public AnchorPane mainPane;
    @FXML public Node structureList;
    @FXML private StructureListController structureListController;

    @FXML private void click(ActionEvent event) {
        ;
    }

    @FXML public void initialize() {
        structureListController.init(this);
    }

    public void init(ControlManager manager) {
        controlManager = manager;
        controlManager.initMainPane(mainPane);
    }

    public void tick() {
        ;
    }
}
