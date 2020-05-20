package managers;

import java.util.ArrayList;
import java.util.Iterator;

import org.Settings;

import javafx.scene.canvas.GraphicsContext;
import objects.Defence;
import objects.Entity;
import objects.Eradicator;
import objects.ForceObject;
import objects.Gas;
import objects.Structure;
import objects.Target;

public class ObjectManager {

    private ArrayList<Structure> structures = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Gas> gas = new ArrayList<>();
    private ArrayList<ForceObject> forceObjects = new ArrayList<>();
    private ArrayList<Eradicator> eradicators = new ArrayList<>();
    private ArrayList<Target> enemies = new ArrayList<>();
    private ArrayList<Defence> defences = new ArrayList<>();

    private void removeDeletedObjects() {
        Iterator<Entity> iterEn = entities.iterator();
        while (iterEn.hasNext()) {
            Entity entity = iterEn.next();
            if (entity.isDeleted()) {
                iterEn.remove();
            }
        }
        Iterator<Gas> iterGas = gas.iterator();
        while (iterGas.hasNext()) {
            Gas gas = iterGas.next();
            if (gas.isDeleted()) {
                iterGas.remove();
            }
        }
        Iterator<ForceObject> iterFobj = forceObjects.iterator();
        while (iterFobj.hasNext()) {
            ForceObject fobj = iterFobj.next();
            if (fobj.isDeleted()) {
                iterFobj.remove();
            }
        }
        Iterator<Eradicator> iterErad = eradicators.iterator();
        while (iterErad.hasNext()) {
            Eradicator eradicator = iterErad.next();
            if (eradicator.isDeleted()) {
                iterErad.remove();
            }
        }
    }

    public void update(double dt) {
        forceObjects.stream().forEach(forceObject -> {
            entities.stream().parallel().forEach(entity -> {
                entity.addAcceleration(forceObject.getForce(entity.getGlobalCenter()));
            });
        });
        eradicators.stream().parallel().forEach(eradicator -> {
            entities.stream().parallel().forEach(entity -> {
                double decrement = eradicator.getDecrement(entity.getGlobalCenter());
                entity.decreaseLifespan(5 * decrement);
                eradicator.decreaseLifespan(4 * decrement);
            });
        });
        defences.stream().forEach(defence -> {
            defence.setTarget(null);
            double minRadius = Settings.get().getTargetRadius();
            for (Target enemy : enemies) {
                double radius = defence.getGlobalCenter().sub(enemy.getGlobalCenter()).magnitude();
                if (radius < minRadius) {
                    defence.setTarget(enemy);
                    minRadius = radius;
                }
            }
        });
        structures.stream().forEach(structure -> structure.update(dt));
        entities.stream().parallel().forEach(entity -> entity.update(dt));
        entities.stream().parallel().forEach(entity -> entity.decreaseLifespan(dt * 100));
        removeDeletedObjects();
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

    public void addEradicator(Eradicator eradicator) {
        eradicators.add(eradicator);
    }

    public void addEnemy(Target enemy) {
        enemies.add(enemy);
    }

    public void addDefence(Defence defence) {
        defences.add(defence);
    }

    public void addGasEntity(Gas gas) {
        this.gas.add(gas);
    }

    public Iterator<Gas> getGasIterator() {
        return gas.iterator();
    }

    public Iterator<Structure> getStructureIterator() {
        return structures.iterator();
    }

}
