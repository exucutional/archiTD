package managers;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import objects.Entity;
import objects.Structure;

public class ObjectManager {

    private ArrayList<Structure> structures = new ArrayList<Structure>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    private void removeDeadEntities() {
        Iterator<Entity> iter = entities.iterator();
        while (iter.hasNext()) {
            Entity entity = iter.next();
            if (entity.isDead()) {
                iter.remove();
            }
        }
    }

    public void update(double dt) {
        structures.stream().forEach(structure -> structure.update(dt));
        entities.stream().parallel().forEach(entity -> entity.update(dt));
        entities.stream().parallel().forEach(entity -> {
            entity.decreaseLifespan(dt * 100);
        });
        removeDeadEntities();
    }

    public void render(GraphicsContext gc) {
        entities.stream().forEach(entity -> entity.render(gc));
    }

    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Iterator<Entity> getEntityIterator() {
        return entities.iterator();
    }

}
