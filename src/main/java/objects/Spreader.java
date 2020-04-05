package objects;

import java.util.Random;

import org.Settings;

import managers.ObjectManager;

public class Spreader extends Structure {

    private double period = 0;
    private double dtAcc = 0;
    private Random random = new Random();
    private ObjectManager objectManager;

    public Spreader(ObjectManager objectManager, double frequency) {
        this.objectManager = objectManager;
        period = 1 / frequency;
    }

    private Entity createParticle() {
        // double vX = 100 * (random.nextGaussian() * 0.3);
        // double vY = 100 * (random.nextGaussian() * 0.3 - 1.0);
        double vX = 200 * (random.nextDouble() - 0.5);
        double vY = 200 * (random.nextDouble() - 0.5);
        Entity entity = new Entity(Settings.get().getParticleLifeSpanMax());
        double halfWidth = Settings.get().getParticleWidth() / 2;
        entity.setPosition(
            getPosition().getX() - halfWidth,
            getPosition().getY() - halfWidth);
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
        if (isActive()) {
            super.update(dt);
            spread(dt);
        }
    }

}