package objects;

import java.util.ArrayList;

import org.Settings;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Structure extends GameObject implements Target {

    private Boolean isEnemy = false;
    private Pane parent;
    private double durability = 0;
    private ArrayList<Connection> connections = new ArrayList<>();

    public Structure() {
        durability = Settings.get().getDefaultDurability();
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

    public void setParent(Pane parent) {
        this.parent = parent;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public Boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(Boolean isEnemy) {
        this.isEnemy = isEnemy;
    }

    public void delete() {
        setDelete(true);
        connections.stream().forEach(connection -> {
            if (!connection.isDeleted()) {
                connection.delete();
            }
        });
        if (parent != null && getView() != null) {
            parent.getChildren().remove(getView());
        }
    }

    @Override
    public Boolean isActive() {
        if (super.isActive()) {
            if (connections.size() > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

}