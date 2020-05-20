package objects;

import org.Settings;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import utility.Vector2D;

public class Connection extends Structure {

    private Line line;

    public Connection(Vector2D begin, Vector2D end) {
        line = new Line(begin.getX(), begin.getY(), end.getX(), end.getY());
        line.setStrokeWidth(Settings.get().getConnectionStroke());
        line.setStroke(Color.BLACK.deriveColor(1, 1, 1, 0.1));
        line.setSmooth(true);
    }

    @Override
    public Node getView() {
        return line;
    }
}