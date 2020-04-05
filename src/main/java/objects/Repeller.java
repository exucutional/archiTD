package objects;

import org.Settings;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import utility.Utils;
import utility.Vector2D;

public class Repeller extends Structure implements ForceObject{

    private double factor = -1;
    private Group node;

    public Repeller() {
        Group group = new Group();
        double radius = 100 / 2;
        Circle circle = new Circle(radius);
        circle.setCenterX(radius);
        circle.setCenterY(radius);
        circle.setStroke(Color.YELLOW);
        circle.setFill(Color.BLACK.deriveColor(1, 1, 1, 0.3));
        group.getChildren().add( circle);
        Text text = new Text("Repeller");
        text.setStroke(Color.YELLOW);
        text.setFill(Color.YELLOW);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.relocate(radius - text.getLayoutBounds().getWidth() / 2, radius - text.getLayoutBounds().getHeight() / 2);
        group.getChildren().add(text);
        node = group;
    }

    public Vector2D getForce(Vector2D position) {
        Vector2D direction = Vector2D.sub(getGlobalCenter(), position);
        double distance = direction.magnitude();
        direction.normalize();
        distance = Utils.clamp(distance, 1, 1000);
        double force = factor * Settings.get().getRepellerStrength() / (distance * distance);
        direction.mul(force);
        return direction;
    }

    @Override
    public Node getView() {
        return node;
    }

    @Override
    public Vector2D getLocalCenter() {
        return new Vector2D(50, 50);
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

}