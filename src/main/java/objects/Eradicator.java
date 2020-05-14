package objects;

import org.Settings;

import utility.Utils;
import utility.Vector2D;

public class Eradicator extends Entity implements ForceObject {

    private double factor = -1;

    public Eradicator(double lifespan) {
        super(lifespan);
    }

    public Vector2D getForce(Vector2D position) {
        Vector2D direction = Vector2D.sub(getGlobalCenter(), position);
        double distance = direction.magnitude();
        direction.normalize();
        distance = Utils.clamp(distance, 20, 1000);
        double force = factor * Settings.get().getRepellerStrength() / (distance * distance);
        direction.mul(force);
        return direction;
    }

    public double getDecrement(Vector2D position) {
        Vector2D direction = Vector2D.sub(getGlobalCenter(), position);
        double distance = direction.magnitude();
        distance = Utils.clamp(distance, 1, 1000);
        return 1 / distance;
    }

}