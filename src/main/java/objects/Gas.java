package objects;

import org.Settings;

public class Gas extends Entity implements DamageObject {

    private double damage = Settings.get().getDefaultDamage();;

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

    public Boolean isEnemy() {
        return true;
    }

    public void delete() {
        setLifespan(0);
    }

}
