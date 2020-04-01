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
    private Point2D position;
    private Point2D velocity;
    private double width;
    private double height;

    public SpriteObject() {
        position = new Point2D(0, 0);
        velocity = new Point2D(0, 0);
    }

    public SpriteObject(double x, double y) {
        position = new Point2D(x, y);
        velocity = new Point2D(0, 0);
    }

    public SpriteObject(final Point2D p) {
        position = new Point2D(p.getX(), p.getY());
        velocity = new Point2D(0, 0);
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
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
    }

    public void setPosition(final Point2D p) {
        setPosition(p.getX(), p.getY());
    }

    public void setCenter(double x, double y) {
        setPosition(x - width / 2, y - height / 2);
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
