package objects;

import org.Settings;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import managers.ObjectManager;
import utility.Vector2D;

public class Turret extends Defence {

    private Structure tower;
    private ObjectManager objectManager;

    public Turret(Structure tower, ObjectManager objectManager) {
        this.tower = tower;
        this.objectManager = objectManager;
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

    public void shoot() {
        Target target = getTarget();
        if (target != null) {
            Vector2D direction = Vector2D.sub(target.getGlobalCenter(), getGlobalCenter());
            direction.normalize();
            double vX = 500 * direction.getX();
            double vY = 500 * direction.getY();
            Eradicator eradicator = new Eradicator(Settings.get().getParticleLifeSpanMax());
            double halfWidth = Settings.get().getParticleWidth() / 2;
            eradicator.setPosition(
                getPosition().getX() - halfWidth,
                getPosition().getY() - halfWidth);
            eradicator.setVelocity(vX, vY);
            eradicator.setAcceleration(0, 0);
            objectManager.addEntity(eradicator);
            objectManager.addForceObject(eradicator);
            objectManager.addEradicator(eradicator);
        }
    }

}