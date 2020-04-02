package managers;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import objects.Structure;

enum ControlMode {
    DEFAULT,
    LAYING,
}

public class ControlManager {

    public double mouseX = 0;
    public double mouseY = 0;
    private ControlMode controlMode;
    private Structure controlStructure;

    public ControlManager() {
        controlMode = ControlMode.DEFAULT;
    }

    public void initMainPane(AnchorPane pane) {
        pane.setOnMouseMoved(event -> {
            if (controlMode == ControlMode.LAYING) {
                Point2D center = controlStructure.getCenter();
                controlStructure.setPosition(event.getX() - center.getX(), event.getY() - center.getY());
            }
            mouseX = event.getX();
            mouseY = event.getY();
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