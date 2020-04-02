package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utility.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class SpriteObject {

    private Image image;
    private ImageView imageView;
    private Vector2D position = new Vector2D(0, 0);
    private Vector2D center = new Vector2D(0, 0);
    private Vector2D velocity = new Vector2D(0, 0);
    private double width;
    private double height;

    public SpriteObject() {
        ;
    }

    public SpriteObject(double x, double y) {
        position = new Vector2D(x, y);
    }

    public SpriteObject(Vector2D v) {
        position = new Vector2D(v);
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

    public void setPosition(double x, double y) {
        position.setVector(x, y);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void setPosition(Vector2D v) {
        setPosition(v.getX(), v.getY());
    }

    public void setLocalCenter(double x, double y) {
        center = new Vector2D(x, y);
    }

    public void setLocalCenter(Vector2D v) {
        setLocalCenter(v.getX(), v.getY());
    }

    public void setVelocity(double x, double y) {
        velocity.setVector(x, y);
    }

    public void setVelocity(Vector2D v) {
        setVelocity(v.getX(), v.getY());
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getCenter() {
        return center;
    }

    public Vector2D getVelocity() {
        return velocity;
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

    public void update(double dt) {
        position.add(velocity.getX() * dt, velocity.getY() * dt);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY());
    }

    public boolean intersects(SpriteObject s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public String toString()
    {
        return "Position: [" + position.getX() + "," + position.getY() + "]" 
        + " Velocity: [" + velocity.getX() + "," + velocity.getY() + "]";
    }
}
