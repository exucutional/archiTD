package managers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import objects.Structure;

enum ControlMode {
    DEFAULT,
    LAYING,
}

public class ControlManager {

    private ControlMode controlMode;
    private Structure controlStructure;

    public ControlManager() {
        controlMode = ControlMode.DEFAULT;
    }

    public void initMainPane(AnchorPane pane) {
        pane.setOnMouseMoved(event -> {
            if (controlMode == ControlMode.LAYING) {
                controlStructure.setCenter(event.getX(), event.getY());
                controlStructure.update(0);
            }
        });
        pane.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && controlMode == ControlMode.LAYING) {
                controlMode = ControlMode.DEFAULT;
            }
        });
    }

    public void placeStructure(Structure structure) {
        controlMode = ControlMode.LAYING;
        controlStructure = structure;
    }

}