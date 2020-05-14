package managers;

import objects.Target;
import utility.Vector2D;

public class MouseTarget implements Target {

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