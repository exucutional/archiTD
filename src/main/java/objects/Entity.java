package objects;

public class Entity extends GameObject implements Target {

    private double lifespan = 0;
    private double maxLifespan = 0;

    public Entity() {

    }

    public Entity(double lifespan) {
        this.lifespan = lifespan;
        this.maxLifespan = lifespan;
    }

    public void setLifespan(double lifespan) {
        this.lifespan = lifespan; 
        this.maxLifespan = lifespan;
    }

    public void decreaseLifespan(double dec) {
        this.lifespan -= dec;
        if (lifespan < 0) {
            setDelete(true);
        }
    }

    public double getLifespan() {
        return lifespan;
    }

    public double getMaxLifespan() {
        return maxLifespan;
    }

}