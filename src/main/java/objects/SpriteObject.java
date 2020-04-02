package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class SpriteObject
{
    private Image image;
    private ImageView imageView;
    private Point2D position = new Point2D(0, 0);
    private Point2D center = new Point2D(0, 0);
    private Point2D velocity = new Point2D(0, 0);
    private double width;
    private double height;

    public SpriteObject() {
        ;
    }

    public SpriteObject(double x, double y) {
        position = new Point2D(x, y);
    }

    public SpriteObject(final Point2D p) {
        position = new Point2D(p.getX(), p.getY());
    }

    public SpriteObject(Image image) {
        setImage(image);
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        center = new Point2D(width / 2, height / 2);
        imageView = new ImageView(image);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y) {
        position = new Point2D(x, y);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void setPosition(final Point2D p) {
        setPosition(p.getX(), p.getY());
    }

    public void setCenter(double x, double y) {
        center = new Point2D(x, y);
    }

    public void setCenter(final Point2D p) {
        setCenter(p.getX(), p.getY());
    }

    public void setVelocity(double x, double y) {
        velocity = new Point2D(x, y);
    }

    public void setVelocity(final Point2D p) {
        setVelocity(p.getX(), p.getY());
    }

    public Point2D getPosition() {
        return new Point2D(position.getX(), position.getY());
    }

    public Point2D getCenter() {
        return new Point2D(center.getX(), center.getY());
    }

    public Point2D getVelocity() {
        return new Point2D(velocity.getX(), velocity.getY());
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), width, height);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void update(double time) {
        position = position.add(velocity.getX() * time, velocity.getY() * time);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY());
    }

    public boolean intersects(final SpriteObject s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public String toString()
    {
        return "Position: [" + position.getX() + "," + position.getY() + "]" 
        + " Velocity: [" + velocity.getX() + "," + velocity.getY() + "]";
    }
}
