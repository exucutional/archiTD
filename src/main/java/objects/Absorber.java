package objects;

import org.Settings;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utility.Utils;

public class Absorber extends Eradicator implements DamageObject {

    private double damage = 0;

    public Absorber(double lifespan) {
        super(lifespan);
        double radius = 20;
        Circle circle = new Circle(radius);
        circle.setCenterX(radius);
        circle.setCenterY(radius);
        circle.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.1));
        setImage(Utils.createImage(circle), false);
        damage = Settings.get().getDefaultDamage();
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void delete() {
        setLifespan(0);
    }

    @Override
    public Boolean isEnemy() {
        return false;
    }

}