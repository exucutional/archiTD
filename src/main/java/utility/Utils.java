package utility;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Utils {

    public static Image createImage(Node node) {
        WritableImage wi;
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        int imageWidth = (int) node.getBoundsInLocal().getWidth();
        int imageHeight = (int) node.getBoundsInLocal().getHeight();
        wi = new WritableImage(imageWidth, imageHeight);
        node.snapshot(parameters, wi);
        return wi;
    }

    public static Image[] createGradientImages() {
        int count = 256;
        double width = count;
        Stop[] stops = new Stop[] {
            new Stop(0, Color.BLACK.deriveColor(1, 1, 1, 0.0)),
            new Stop(0.3, Color.RED),
            new Stop(0.9, Color.YELLOW),
            new Stop(1, Color.WHITE)
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, width, 0, false, CycleMethod.NO_CYCLE, stops);
        Rectangle rectangle = new Rectangle(width, 1);
        rectangle.setFill(linearGradient);
        Image lookupImage = createImage(rectangle);
        Image[] list = new Image[count];
        double radius = 1;
        for (int i = 0; i < count; i++) {
            Color color = lookupImage.getPixelReader().getColor(i, 0);
            Circle ball = new Circle(radius);
            RadialGradient gradient = new RadialGradient(
                0, 0, 0, 0,
                radius, false, CycleMethod.NO_CYCLE,
                new Stop(0, color.deriveColor(1, 1, 1, 1)), new Stop(1, color.deriveColor(1, 1, 1, 0)));
            ball.setFill(gradient);
            list[i] = Utils.createImage(ball);
        }
        return list;
    }

}