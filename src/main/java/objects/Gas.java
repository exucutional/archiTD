package objects;

public class Gas extends Entity {

    private double damage = 0;

    public Gas() {
        ;
    }

    public Gas(double lifespan) {
        super(lifespan);
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

}
