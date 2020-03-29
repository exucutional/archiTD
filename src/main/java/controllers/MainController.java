package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML private StructureListController structureListController;

    @FXML private void click(ActionEvent event) {
        ;
    }

    @FXML public void initialize() {
        structureListController.init(this);
    }

    public void tick() {
        ;
    }
}
