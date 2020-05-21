package managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.Settings;

import javafx.scene.canvas.GraphicsContext;
import objects.DamageObject;
import objects.Defence;
import objects.Entity;
import objects.Eradicator;
import objects.ForceObject;
import objects.Gas;
import objects.Structure;
import objects.Target;

public class ObjectManager {

    private double gasLimit = Settings.get().getGasLimit();

    private ArrayList<Structure> structures = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Gas> gas = new ArrayList<>();
    private ArrayList<ForceObject> forceObjects = new ArrayList<>();
    private ArrayList<Eradicator> eradicators = new ArrayList<>();
    private ArrayList<Target> enemies = new ArrayList<>();
    private ArrayList<Defence> defences = new ArrayList<>();
    private ArrayList<DamageObject> damageObjects = new ArrayList<>();

    private void removeDeletedObjects() {
        Iterator<Entity> iterEn = entities.iterator();
        while (iterEn.hasNext()) {
            Entity entity = iterEn.next();
            if (entity.isDeleted()) {
                iterEn.remove();
            }
        }
        Iterator<DamageObject> iterDobj = damageObjects.iterator();
        while (iterDobj.hasNext()) {
            DamageObject dObj = iterDobj.next();
            if (dObj.isDeleted()) {
                iterDobj.remove();
            }
        }
        Iterator<Gas> iterGas = gas.iterator();
        while (iterGas.hasNext()) {
            Gas gas = iterGas.next();
            if (gas.isDeleted()) {
                iterGas.remove();
            }
        }
        Iterator<Structure> iterStr = structures.iterator();
        while (iterStr.hasNext()) {
            Structure structure = iterStr.next();
            if (structure.getDurability() <= 0) {
                iterStr.remove();
                structure.delete();
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
        Iterator<Target> iterTarget = enemies.iterator();
        while (iterTarget.hasNext()) {
            Target target = iterTarget.next();
            if (target.isDeleted()) {
                iterTarget.remove();
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
                if (eradicator.isEnemy() != entity.isEnemy()) {
                    double decrement = eradicator.getDecrement(entity.getGlobalCenter());
                    entity.decreaseLifespan(5 * decrement);
                    eradicator.decreaseLifespan(3 * decrement);
                }
            });
        });
        defences.stream().forEach(defence -> {
            Target target = null;
            double minRadius = Settings.get().getTargetRadius();
            for (Target enemy : enemies) {
                double radius = defence.getGlobalCenter().sub(enemy.getGlobalCenter()).magnitude();
                if (radius < minRadius) {
                    target = enemy;
                    minRadius = radius;
                }
            }
            if (target == null) {
                int gasLimit = 50;
                for (Gas enemy : gas) {
                    int count = 0;
                    double radius = defence.getGlobalCenter().sub(enemy.getGlobalCenter()).magnitude();
                    if (radius < minRadius) {
                        target = enemy;
                        minRadius = radius;
                        if (count++ > gasLimit) {
                            break;
                        }
                    }
                }
            }
            defence.setTarget(target);
        });
        damageObjects.stream().forEach(dObj -> {
            for (Structure structure : structures) {
                if (dObj.isEnemy() != structure.isEnemy() && structure.isActive()) {
                    if (dObj.getBoundary().intersects(structure.getBoundary())) {
                        structure.setDurability(structure.getDurability() - dObj.getDamage());
                        dObj.delete();
                    }
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
        if (this.gas.size() < gasLimit) {
            this.gas.add(gas);
        }
    }

    public void addDamageObject(DamageObject dobj) {
        this.damageObjects.add(dobj);
    }

    public Iterator<Gas> getGasIterator() {
        return gas.iterator();
    }

    public Iterator<Structure> getStructureIterator() {
        return structures.iterator();
    }

    public Target getRandomTarget() {
        Random random = new Random();
        if (enemies.size() == 0) {
            return null;
        }
        return enemies.get((int) Math.floor(random.nextDouble() * enemies.size()));
    }

}
