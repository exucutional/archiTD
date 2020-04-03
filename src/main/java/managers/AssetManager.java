package managers;

import java.util.HashMap;
import java.util.Map;

import org.Settings;

import javafx.scene.image.Image;
import utility.Utils;

public class AssetManager {

    private Map<String, Image> images = new HashMap<String, Image>();

    public void init() {
        Image[] circles = Utils.createGradientCircles();
        for (int i = 0; i < Settings.get().getParticleLifeSpanMax(); i++) {
            images.put(String.format("entity-gas-%d", i), circles[i]);
        }
        images.put("structure-hub", new Image("/assets/structures/hub.png"));
        images.put("structure-tower-simple", new Image("/assets/structures/tower-simple.png"));
        images.put("structure-turret-simple", new Image("/assets/structures/turret-simple.png"));
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
