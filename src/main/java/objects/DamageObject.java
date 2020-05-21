package objects;

import javafx.geometry.Rectangle2D;

public interface DamageObject {

    public abstract double getDamage();
    public abstract Boolean isEnemy();
    public abstract Rectangle2D getBoundary();
    public abstract void delete();
    public abstract Boolean isDeleted();

}