package managers;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class AssetManager {

    private Map<String, Image> images = new HashMap<String, Image>();

    public void init() {
        images.put("structure-hub", new Image("/assets/structures/hub.png"));
        images.put("structure-tower-simple", new Image("/assets/structures/tower-simple.png"));
        images.put("structure-turret-simple", new Image("/assets/structures/tower-simple.png"));
    }

    public Image putImage(String key, Image image) {
        return images.put(key, image);
    }

    public Image getImage(String key) {
        return images.get(key);
    }

    public Image removeImage(String key) {
        return images.remove(key);
    }

    public Boolean containsImage(String key) {
        return images.containsKey(key);
    }

    public int sizeImages() {
        return images.size();
    }
}
