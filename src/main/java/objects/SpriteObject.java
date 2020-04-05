package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utility.Vector2D;
import javafx.scene.Node;
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

    public SpriteObject(Image image, boolean imageViewEnable) {
        setImage(image, imageViewEnable);
    }

    public void setImage(Image i, boolean imageViewEnable) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        if (imageViewEnable) {
            initImageView();
        }
        setLocalCenter(width / 2, height / 2);
    }

    public void initImageView() {
        imageView = new ImageView(image);
        imageView.setX(position.getX());
        imageView.setY(position.getY());
    }

    public void setImage(String filename, boolean imageViewEnable) {
        Image i = new Image(filename);
        setImage(i, imageViewEnable);
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        if (imageView != null) {
            imageView.setX(position.getX());
            imageView.setY(position.getY());
        }
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

    public Vector2D getLocalCenter() {
        return center;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), width, height);
    }

    public Node getView() {
        return imageView;
    }

    public Vector2D getGlobalCenter() {
        Vector2D c = getLocalCenter();
        return new Vector2D(position.getX() + c.getX(), position.getY() + c.getY());
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (imageView != null) {
            imageView.setX(position.getX());
            imageView.setY(position.getY());
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY());
    }

    public boolean intersects(SpriteObject s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
