package objects;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Turret extends Defence {

    private Structure tower;

    public Turret(Structure tower) {
        this.tower = tower;
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        tower.setPosition(x, y);
    }

    @Override
    public void setPosition(Point2D p) {
        super.setPosition(p);
        tower.setPosition(p);
    }

    @Override
    public void update(double dt) {
        if (isActive()) {
            super.update(dt);
            ImageView imageView = getImageView();
            Rotate rotation = new Rotate(imageView.getRotate() + 100 * dt, getGlobalCenter().getX(), getGlobalCenter().getY());
            imageView.getTransforms().add(rotation);
        }
    }

}