package objects;

import javafx.scene.image.Image;

public class Structure extends GameObject implements Target {

    private double durability = 0;

    public Structure() {

    }

    public Structure(Image image, boolean imageViewEnable) {
        setImage(image, imageViewEnable);
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }

    public double getDurability() {
        return durability;
    }

}