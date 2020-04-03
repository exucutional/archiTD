package objects;

import java.util.Random;

import org.Settings;

import managers.ObjectManager;

public class Spreader extends Structure {

    private double frequency = 0;
    private double period = 0;
    private double dtAcc = 0;
    private Random random = new Random();
    private ObjectManager objectManager;

    public Spreader(ObjectManager objectManager, double frequency) {
        this.objectManager = objectManager;
        this.frequency = frequency;
        period = 1 / frequency;
    }

    private Entity createParticle() {
        // double vX = 100 * (random.nextGaussian() * 0.3);
        // double vY = 100 * (random.nextGaussian() * 0.3 - 1.0);
        double vX = 100 * (random.nextDouble() - 0.5);
        double vY = 100 * (random.nextDouble() - 0.5);
        Entity entity = new Entity(Settings.get().getParticleLifeSpanMax());
        entity.setPosition(getPosition());
        entity.setVelocity(vX, vY);
        entity.setAcceleration(0, 0);
        return entity;
    }

    public void spread(double dt) {
        dtAcc += dt;
        if (dtAcc >= period) {
            objectManager.addEntity(createParticle());
            dtAcc = 0;
        }
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (isActive()) {
            spread(dt);
        }
    }

}