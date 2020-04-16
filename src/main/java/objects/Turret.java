package objects;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import utility.Vector2D;

public class Turret extends Defence {

    private Structure tower;

    public Turret(Structure tower) {
        this.tower = tower;
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        tower.setPosition(x, y);
    }

    @Override
    public void setPosition(Vector2D p) {
        super.setPosition(p);
        tower.setPosition(p);
    }

    @Override
    public void update(double dt) {
        if (isActive()) {
            super.update(dt);
            Target target = getTarget();
            if (target != null) {
                Node imageView = getView();
                Vector2D direction = Vector2D.sub(target.getGlobalCenter(), getGlobalCenter());
                double angle = Math.toDegrees(Vector2D.getAngle(direction, new Vector2D(1, 0)));
                if (direction.getY() < 0) {
                    angle = 360 - angle;
                }
                if (!Double.isNaN(angle)) {
                    Rotate rotation = new Rotate(angle, getGlobalCenter().getX(), getGlobalCenter().getY());
                    ObservableList<Transform> transform = imageView.getTransforms();
                    transform.clear();
                    transform.add(rotation);
                }
            }
        }
    }

}