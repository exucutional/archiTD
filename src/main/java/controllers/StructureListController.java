package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StructureListController {

    private MainController mainController;

    @FXML private Button HubButton;
    @FXML private Button Structure1Button;

    public void init(MainController controller) {
        mainController = controller;
    }
}
