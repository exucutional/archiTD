package objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utility.Utils;

public class Absorber extends Eradicator {

    public Absorber(double lifespan) {
        super(lifespan);
        double radius = 20;
        Circle circle = new Circle(radius);
        circle.setCenterX(radius);
        circle.setCenterY(radius);
        circle.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.1));
        setImage(Utils.createImage(circle), false);
    }

}