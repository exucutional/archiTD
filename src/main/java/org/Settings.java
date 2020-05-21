package org;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Settings {

    private static Settings settings = new Settings();

    private Settings() {
    }

    public static Settings get() {
        return settings;
    }

    private DoubleProperty particleWidth = new SimpleDoubleProperty(20);
    private DoubleProperty particleHeight = new SimpleDoubleProperty(particleWidth.doubleValue());
    private DoubleProperty particleLifeSpanMax = new SimpleDoubleProperty(1024);
    private DoubleProperty particleMaxSpeed = new SimpleDoubleProperty(4);
    private DoubleProperty repellerStrength = new SimpleDoubleProperty(500000);
    private DoubleProperty connectionStroke = new SimpleDoubleProperty(5);
    private DoubleProperty placeRadius = new SimpleDoubleProperty(300);
    private DoubleProperty shootRate = new SimpleDoubleProperty(0.5);
    private DoubleProperty targetRadius = new SimpleDoubleProperty(400);
    private DoubleProperty defaultDurability = new SimpleDoubleProperty(50);
    private DoubleProperty defaultDamage = new SimpleDoubleProperty(10);
    private DoubleProperty windowWidth = new SimpleDoubleProperty(1280);
    private DoubleProperty windowHeight = new SimpleDoubleProperty(720);
    private DoubleProperty gasLimit = new SimpleDoubleProperty(10000);

    public final double getParticleWidth() {
        return particleWidth.get();
    }

    public final double getParticleHeight() {
        return particleHeight.get();
    }

    public final double getParticleLifeSpanMax() {
        return particleLifeSpanMax.get();
    }

    public final double getParticleMaxSpeed() {
        return particleMaxSpeed.get();
    }

    public final double getRepellerStrength() {
        return repellerStrength.get();
    }

    public final double getConnectionStroke() {
        return connectionStroke.get();
    }

    public final double getPlaceRadius() {
        return placeRadius.get();
    }

    public final double getShootRate() {
        return shootRate.get();
    }

    public final double getTargetRadius() {
        return targetRadius.get();
    }

    public final double getDefaultDurability() {
        return defaultDurability.get();
    }

    public final double getDefaultDamage() {
        return defaultDamage.get();
    }

    public final double getWindowWidth() {
        return windowWidth.get();
    }

    public final double getWindowHeight() {
        return windowHeight.get();
    }

    public final double getGasLimit() {
        return gasLimit.get();
    }

}
