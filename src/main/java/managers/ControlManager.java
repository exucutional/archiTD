package managers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import objects.Structure;

public class ControlManager {

    private String controlMode;
    private Structure controlStructure;

    public ControlManager() {
        controlMode = "default";
    }

    public void initMainPane(AnchorPane pane) {
        pane.setOnMouseMoved(event -> {
            if (controlMode == "laying") {
                controlStructure.setCenter(event.getX(), event.getY());
                controlStructure.update(0);
            }
        });
        pane.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && controlMode == "laying") {
                controlMode = "default";
            }
        });
    }

    public void placeStructure(Structure structure) {
        controlMode = "laying";
        controlStructure = structure;
    }

}