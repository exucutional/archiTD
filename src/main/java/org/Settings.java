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

}
