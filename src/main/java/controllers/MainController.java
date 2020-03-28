package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class MainController {

    @FXML private Button left;
    @FXML private Button right;
    @FXML private Button up;
    @FXML private Button down;
    @FXML private Circle circle;
    int newX, newY = 0;
    @FXML
    private void click(ActionEvent event) {
        newY = newY - 10;
        System.out.println(newY);
        circle.setTranslateY(newY);
    }

    public void tick() {
        
    }
}