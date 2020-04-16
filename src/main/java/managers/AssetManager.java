package managers;

import java.util.HashMap;
import java.util.Map;

import org.Settings;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import utility.Utils;

public class AssetManager {

    private Map<String, Image> images = new HashMap<String, Image>();

    public void init() {
        Stop[] stops = new Stop[] {
            new Stop(0, Color.BLACK.deriveColor(1, 1, 1, 0.0)),
            new Stop(0.3, Color.RED),
            new Stop(0.9, Color.YELLOW),
            new Stop(1, Color.WHITE)
        };
        Image[] circles = Utils.createGradientCircles(stops, (int) Settings.get().getParticleLifeSpanMax(), Settings.get().getParticleWidth());
        for (int i = 0; i < Settings.get().getParticleLifeSpanMax(); i++) {
            images.put(String.format("entity-gas-%d", i), circles[i]);
        }
        // Stop[] bulletStops = new Stop[] {
        //     new Stop(0, Color.BLACK.deriveColor(1, 1, 1, 0.0)),
        //     new Stop(1, Color.BLUE),
        // };
        // Image[] bullets = Utils.createGradientCircles(bulletStops, (int) Settings.get().getParticleLifeSpanMax(), 10);
        // for (int i = 0; i < Settings.get().getParticleLifeSpanMax(); i++) {
        //     images.put(String.format(""));
        // }
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
