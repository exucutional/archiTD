package managers;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import objects.Entity;
import objects.ForceObject;
import objects.Structure;

public class ObjectManager {

    private ArrayList<Structure> structures = new ArrayList<Structure>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<ForceObject> forceObjects = new ArrayList<ForceObject>();

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
        forceObjects.stream().forEach(forceObject -> {
            entities.stream().parallel().forEach(entity -> {
                entity.setAcceleration(forceObject.getForce(entity.getGlobalCenter()));
            });
        });
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

    public void addForceObject(ForceObject forceObject) {
        forceObjects.add(forceObject);
    }

    public Iterator<Entity> getEntityIterator() {
        return entities.iterator();
    }

}
