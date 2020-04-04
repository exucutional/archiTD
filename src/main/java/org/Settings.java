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
    private DoubleProperty particleLifeSpanMax = new SimpleDoubleProperty(512);
    private DoubleProperty particleMaxSpeed = new SimpleDoubleProperty(4);

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

}