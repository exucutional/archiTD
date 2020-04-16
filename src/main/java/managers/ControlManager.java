package managers;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import objects.Structure;
import objects.Target;
import utility.Vector2D;

enum ControlMode {
    DEFAULT,
    LAYING,
}

class MouseTarget implements Target {

    private Vector2D position = new Vector2D();

    public void setPosition(double x, double y) {
        position.setVector(x, y);
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getGlobalCenter() {
        return getPosition();
    }

}

public class ControlManager {

    public double mouseX = 0;
    public double mouseY = 0;
    public MouseTarget mouseTarget;
    private ControlMode controlMode;
    private Structure controlStructure;

    public ControlManager() {
        controlMode = ControlMode.DEFAULT;
        mouseTarget = new MouseTarget();
    }

    public void initMainPane(AnchorPane pane) {
        pane.setOnMouseMoved(event -> {
            if (controlMode == ControlMode.LAYING) {
                Vector2D center = controlStructure.getLocalCenter();
                controlStructure.setPosition(event.getX() - center.getX(), event.getY() - center.getY());
            }
            mouseX = event.getX();
            mouseY = event.getY();
            mouseTarget.setPosition(mouseX, mouseY);
        });
        pane.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && controlMode == ControlMode.LAYING) {
                controlMode = ControlMode.DEFAULT;
                controlStructure.setActive(true);
            }
        });
    }

    public void placeStructure(Structure structure) {
        controlMode = ControlMode.LAYING;
        controlStructure = structure;
    }

}