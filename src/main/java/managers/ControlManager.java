package managers;

import events.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import objects.Structure;
import utility.Vector2D;

enum ControlMode {
    DEFAULT,
    LAYING,
}

public class ControlManager {

    public double mouseX = 0;
    public double mouseY = 0;
    public MouseTarget mouseTarget;

    private ControlMode controlMode;
    private Structure controlStructure;
    private EventManager eventManager;

    public ControlManager() {
        controlMode = ControlMode.DEFAULT;
        mouseTarget = new MouseTarget();
    }

    public void init(AnchorPane pane, EventManager eventManager) {
        this.eventManager = eventManager;
        initMainPane(pane);
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
                eventManager.publish(EventType.PLACE);
            } else if (event.getButton().equals(MouseButton.PRIMARY)) {
                eventManager.publish(EventType.SHOOT);
            }
        });
    }

    public void placeStructure(Structure structure) {
        controlMode = ControlMode.LAYING;
        controlStructure = structure;
    }

}
