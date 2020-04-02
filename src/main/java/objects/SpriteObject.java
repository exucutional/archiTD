package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utility.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class SpriteObject extends Object {

    private Image image;
    private ImageView imageView;
    private Vector2D center = new Vector2D(0, 0);
    private double width;
    private double height;

    public SpriteObject() {
        ;
    }

    public SpriteObject(double x, double y) {
        super(x, y);
    }

    public SpriteObject(Vector2D v) {
        super(v);
    }

    public SpriteObject(Image image) {
        setImage(image);
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        imageView = new ImageView(image);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
        setLocalCenter(width / 2, height / 2);
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    @Override
    public void setPosition(Vector2D v) {
        setPosition(v.getX(), v.getY());
    }

    public void setLocalCenter(double x, double y) {
        center.setVector(x, y);
    }

    public void setLocalCenter(Vector2D v) {
        setLocalCenter(v.getX(), v.getY());
    }

    public Vector2D getCenter() {
        return center;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), width, height);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Vector2D getGlobalCenter() {
        return new Vector2D(position.getX() + center.getX(), position.getY() + center.getY());
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY());
    }

    public boolean intersects(SpriteObject s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
