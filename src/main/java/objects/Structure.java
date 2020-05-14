package objects;

import javafx.scene.image.Image;

public class Structure extends GameObject implements Target {

    public Structure() {

    }

    public Structure(Image image, boolean imageViewEnable) {
        setImage(image, imageViewEnable);
    }

}