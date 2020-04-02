package objects;

import javafx.geometry.Point2D;

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

}