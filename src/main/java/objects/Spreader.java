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

    private Gas createParticle() {
        // double vX = 100 * (random.nextGaussian() * 0.3);
        // double vY = 100 * (random.nextGaussian() * 0.3 - 1.0);
        double vX = 200 * (random.nextDouble() - 0.5);
        double vY = 200 * (random.nextDouble() - 0.5);
        Gas gas = new Gas(Settings.get().getParticleLifeSpanMax());
        double halfWidth = Settings.get().getParticleWidth() / 2;
        gas.setPosition(
            getPosition().getX() - halfWidth,
            getPosition().getY() - halfWidth);
        gas.setVelocity(vX, vY);
        gas.setAcceleration(0, 0);
        return gas;
    }

    public void spread(double dt) {
        dtAcc += dt;
        while (dtAcc >= period) {
            Gas particle = createParticle();
            objectManager.addEntity(particle);
            objectManager.addGasEntity(particle);
            objectManager.addDamageObject(particle);
            dtAcc -= period;
        }
    }

    @Override
    public void update(double dt) {
        if (isActive()) {
            super.update(dt);
            spread(dt);
        }
    }

    @Override
    public Boolean isEnemy() {
        return true;
    }
    
    @Override
    public void delete() {
        for (int i = 0; i < 500; i++) {
            Gas particle = createParticle();
            particle.getVelocity().mul(2);
            objectManager.addEntity(particle);
            objectManager.addGasEntity(particle);
            objectManager.addDamageObject(particle);
        }
        super.delete();
    }

}